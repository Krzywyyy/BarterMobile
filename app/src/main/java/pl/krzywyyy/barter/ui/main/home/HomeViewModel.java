package pl.krzywyyy.barter.ui.main.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pl.krzywyyy.barter.MyApplication;
import pl.krzywyyy.barter.api.ProductInterface;
import pl.krzywyyy.barter.model.domain.Product;
import pl.krzywyyy.barter.model.domain.ProductView;
import pl.krzywyyy.barter.utils.ImageEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.internal.EverythingIsNonNull;

public class HomeViewModel extends ViewModel {

    @Inject
    Retrofit retrofit;

    private MutableLiveData<List<ProductView>> productViews;
    private MutableLiveData<List<ProductView>> newProducts;

    public HomeViewModel() {
        MyApplication.appComponent.inject(this);
        productViews = getProducts(1);
        newProducts = new MutableLiveData<>();
    }

    MutableLiveData<List<ProductView>> getProductViews() {
        return productViews;
    }

    MutableLiveData<List<ProductView>> getNewProducts() {
        return newProducts;
    }

    void loadNextProducts(int page) {
        newProducts = getProducts(page);
    }

    private MutableLiveData<List<ProductView>> getProducts(int page) {
        ProductInterface productService = retrofit.create(ProductInterface.class);

        Call<List<Product>> call = productService.findAll(page);

        MutableLiveData<List<ProductView>> products = new MutableLiveData<>();

        call.enqueue(new Callback<List<Product>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().size() == 0) {
                        return;
                    }
                    List<ProductView> newProductViews = new ArrayList<>();

                    for (Product product : response.body()) {
                        newProductViews.add(new ProductView(
                                product.getId(),
                                product.getTitle(),
                                ImageEncoder.toImage(product.getImage())
                        ));
                    }
                    products.setValue(newProductViews);
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        });
        return products;
    }
}

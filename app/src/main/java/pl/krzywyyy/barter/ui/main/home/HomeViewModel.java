package pl.krzywyyy.barter.ui.main.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pl.krzywyyy.barter.MyApplication;
import pl.krzywyyy.barter.api.barter.ProductInterface;
import pl.krzywyyy.barter.model.domain.Product;
import pl.krzywyyy.barter.model.domain.ProductView;
import pl.krzywyyy.barter.utils.ImageDecoder;
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

    private String searchPhrase;
    private String category;
    private String specialization;
    private Integer distance;
    private Float latitude;
    private Float longitude;

    public HomeViewModel(String searchPhrase, String category, String specialization, Integer distance, Float latitude, Float longitude) {
        MyApplication.appComponent.inject(this);
        this.searchPhrase = searchPhrase;
        this.category = category;
        this.specialization = specialization;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;

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

        Call<List<Product>> call = productService.findAll(page, category, specialization, searchPhrase, distance, latitude, longitude);

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
                                ImageDecoder.toImage(product.getImage())
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

package pl.krzywyyy.barter.ui.main.userproducts;

import androidx.lifecycle.MutableLiveData;

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

public class UserProductsRepository {

    private static UserProductsRepository userProductsRepository;

    @Inject
    Retrofit retrofit;

    public UserProductsRepository() {
        MyApplication.appComponent.inject(this);
    }

    public static UserProductsRepository getInstance() {
        if (userProductsRepository == null) {
            userProductsRepository = new UserProductsRepository();
        }
        return userProductsRepository;
    }

    public MutableLiveData<List<ProductView>> getUserProducts() {
        ProductInterface productService = retrofit.create(ProductInterface.class);

        Call<List<Product>> call = productService.findAllUserProducts();

        MutableLiveData<List<ProductView>> products = new MutableLiveData<>();

        call.enqueue(new Callback<List<Product>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ProductView> productViews = new ArrayList<>();
                    for (Product product : response.body()) {
                        productViews.add(new ProductView(
                                product.getTitle(),
                                ImageEncoder.toImage(product.getImage())
                        ));
                    }
                    products.setValue(productViews);
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

package pl.krzywyyy.barter.ui.main.productdetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import pl.krzywyyy.barter.MyApplication;
import pl.krzywyyy.barter.api.ProductInterface;
import pl.krzywyyy.barter.model.domain.Product;
import pl.krzywyyy.barter.model.domain.ProductDetail;
import pl.krzywyyy.barter.utils.ImageEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.internal.EverythingIsNonNull;

public class ProductDetailsViewModel extends ViewModel {

    @Inject
    Retrofit retrofit;

    private MutableLiveData<ProductDetail> productDetail;

    public ProductDetailsViewModel(int productId) {
        MyApplication.appComponent.inject(this);
        productDetail = getProduct(productId);
    }

    public MutableLiveData<ProductDetail> getProductDetail() {
        return productDetail;
    }

    private MutableLiveData<ProductDetail> getProduct(int productId) {
        ProductInterface productService = retrofit.create(ProductInterface.class);

        Call<Product> call = productService.find(productId);

        MutableLiveData<ProductDetail> product = new MutableLiveData<>();

        call.enqueue(new Callback<Product>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if(response.isSuccessful() && response.body() != null){
                    ProductDetail productDetail = new ProductDetail();
                    productDetail.setTitle(response.body().getTitle());
                    productDetail.setDescription(response.body().getDescription());
                    productDetail.setSpecialization(response.body().getSpecialization());
                    productDetail.setImage(ImageEncoder.toImage(response.body().getImage()));
                    product.setValue(productDetail);
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
        return product;
    }
}


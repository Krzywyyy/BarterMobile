package pl.krzywyyy.barter.ui.main.productdetails;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import pl.krzywyyy.barter.MyApplication;
import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.api.barter.OfferInterface;
import pl.krzywyyy.barter.api.barter.ProductInterface;
import pl.krzywyyy.barter.model.domain.Offer;
import pl.krzywyyy.barter.model.domain.Product;
import pl.krzywyyy.barter.model.domain.ProductDetail;
import pl.krzywyyy.barter.utils.ImageDecoder;
import pl.krzywyyy.barter.utils.dialogs.DeleteDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.internal.EverythingIsNonNull;

public class ProductDetailsViewModel extends ViewModel {

    @Inject
    Retrofit retrofit;

    private MutableLiveData<ProductDetail> productDetail;
    private MutableLiveData<List<Offer>> productOffers;

    public ProductDetailsViewModel(int productId) {
        MyApplication.appComponent.inject(this);
        productDetail = getProduct(productId);
        productOffers = getOffers(productId);
    }

    MutableLiveData<List<Offer>> getProductOffers() {
        return productOffers;
    }

    MutableLiveData<ProductDetail> getProductDetail() {
        return productDetail;
    }

    void removeProduct(Context context, int productId) {
        DeleteDialog.show(context, getDeleteConfirmListener(context, productId));
    }

    private MutableLiveData<List<Offer>> getOffers(int productId) {
        OfferInterface offerService = retrofit.create(OfferInterface.class);

        Call<List<Offer>> call = offerService.findAllByProduct(productId);
        MutableLiveData<List<Offer>> productOffers = new MutableLiveData<>();

        call.enqueue(new Callback<List<Offer>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productOffers.setValue(response.body());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<Offer>> call, Throwable t) {

            }
        });
        return productOffers;

    }

    private DialogInterface.OnClickListener getDeleteConfirmListener(Context context, int productId) {
        return (dialogInterface, i) -> {
            ProductInterface productService = retrofit.create(ProductInterface.class);

            Call<Void> call = productService.delete(productId);

            call.enqueue(new Callback<Void>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, R.string.successfully_deleted_product, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, R.string.unsuccessfully_deleted_product, Toast.LENGTH_SHORT).show();
                    }
                }

                @EverythingIsNonNull
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(context, R.string.failed_retrofit_request_toast, Toast.LENGTH_SHORT).show();
                }
            });
        };
    }

    private MutableLiveData<ProductDetail> getProduct(int productId) {
        ProductInterface productService = retrofit.create(ProductInterface.class);

        Call<Product> call = productService.find(productId);

        MutableLiveData<ProductDetail> product = new MutableLiveData<>();

        call.enqueue(new Callback<Product>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductDetail productDetail = new ProductDetail();
                    productDetail.setTitle(response.body().getTitle());
                    productDetail.setDescription(response.body().getDescription());
                    productDetail.setSpecialization(response.body().getSpecialization());
                    productDetail.setAddress(response.body().getAddress());
                    productDetail.setImage(ImageDecoder.toImage(response.body().getImage()));
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


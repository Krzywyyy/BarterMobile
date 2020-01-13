package pl.krzywyyy.barter.ui.main.newproduct;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import java.util.List;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import pl.krzywyyy.barter.MyApplication;
import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.api.barter.ProductInterface;
import pl.krzywyyy.barter.api.googlemaps.AddressInterface;
import pl.krzywyyy.barter.di.googlemapsretrofit.GoogleMapsRetrofit;
import pl.krzywyyy.barter.model.domain.Product;
import pl.krzywyyy.barter.model.enums.ProductCategory;
import pl.krzywyyy.barter.model.enums.Specialization;
import pl.krzywyyy.barter.model.maps.Result;
import pl.krzywyyy.barter.model.maps.Results;
import pl.krzywyyy.barter.utils.ImageEncoder;
import pl.krzywyyy.barter.utils.dialogs.DiscardChangesDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.internal.EverythingIsNonNull;

@Getter
@Setter
public class NewProductViewModel extends BaseObservable {

    @Inject
    Retrofit retrofit;

    private Product product;
    private SpinnerAdapter productCategoryAdapter;
    private SpinnerAdapter specializationAdapter;
    private Bitmap productImage;

    private String address;

    NewProductViewModel(Context context) {
        MyApplication.appComponent.inject(this);
        product = new Product();
        product.setTitle("");
        product.setDescription("");
        productCategoryAdapter = new ArrayAdapter<>(context, R.layout.spinner_list_item, R.id.spinner_item, ProductCategory.values());
        specializationAdapter = new ArrayAdapter<>(context, R.layout.spinner_list_item, R.id.spinner_item, Specialization.values());
        productImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.no_image);
    }

    void tryToGetCoordinatesAndAddNewProduct(Context context) {
        Retrofit retrofit = GoogleMapsRetrofit.getInstance();
        AddressInterface addressService = retrofit.create(AddressInterface.class);

        Call<Result> getCoordinates = addressService.getCoordinates(address, context.getResources().getString(R.string.google_maps_key));

        getCoordinates.enqueue(new Callback<Result>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Results> results = response.body().getResults();
                    if (results.size() > 0) {
                        Results result = results.get(0);
                        product.setAddress(result.getFormatted_address());
                        product.setLatitude(result.getGeometry().getLocation().getLat());
                        product.setLongitude(result.getGeometry().getLocation().getLng());
                    }
                    addNewProduct(context);
                } else {
                    Toast.makeText(context, R.string.cannot_get_address, Toast.LENGTH_SHORT).show();
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(context, R.string.failed_retrofit_request_toast, Toast.LENGTH_SHORT).show();
            }
        });
    }

    void cancel(Context context, NewProductFragment newProductFragment) {
        if (fieldsAreNotEmpty()) {
            DiscardChangesDialog.show(context, newProductFragment);
        } else {
            newProductFragment.dismiss();
        }
    }

    private void addNewProduct(Context context) {
        product.setImage(ImageEncoder.toBase64(productImage));

        ProductInterface productService = retrofit.create(ProductInterface.class);
        Call<Product> call = productService.save(product);

        call.enqueue(new Callback<Product>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, R.string.successfully_added_new_product, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, R.string.unsuccessfully_added_new_product, Toast.LENGTH_SHORT).show();
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(context, R.string.failed_retrofit_request_toast, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean fieldsAreNotEmpty() {
        return product.getTitle().length() > 0 || product.getDescription().length() > 0;
    }
}

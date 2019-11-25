package pl.krzywyyy.barter.ui.main.newproduct;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import pl.krzywyyy.barter.MyApplication;
import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.api.ProductInterface;
import pl.krzywyyy.barter.model.domain.Product;
import pl.krzywyyy.barter.model.enums.ProductCategory;
import pl.krzywyyy.barter.model.enums.Specialization;
import pl.krzywyyy.barter.utils.ImageEncoder;
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

    public NewProductViewModel(Context context) {
        MyApplication.appComponent.inject(this);
        product = new Product();
        productCategoryAdapter = new ArrayAdapter<>(context, R.layout.spinner_list_item, R.id.spinner_item, ProductCategory.values());
        specializationAdapter = new ArrayAdapter<>(context, R.layout.spinner_list_item, R.id.spinner_item, Specialization.values());
        productImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.no_image);
    }

    public void addNewProduct(Context context) {
        product.setImage(ImageEncoder.toBase64(productImage));

        ProductInterface productService = retrofit.create(ProductInterface.class);
        Call<Product> call = productService.save(product);

        call.enqueue(new Callback<Product>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, R.string.successfully_added_new_product, Toast.LENGTH_SHORT).show();
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(context, R.string.failed_retrofit_request_toast, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

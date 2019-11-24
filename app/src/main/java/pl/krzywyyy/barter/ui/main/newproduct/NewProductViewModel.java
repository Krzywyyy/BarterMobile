package pl.krzywyyy.barter.ui.main.newproduct;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import pl.krzywyyy.barter.MyApplication;
import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.model.domain.Product;
import pl.krzywyyy.barter.model.enums.ProductCategory;
import pl.krzywyyy.barter.model.enums.Specialization;
import retrofit2.Retrofit;

@Getter
@Setter
public class NewProductViewModel extends BaseObservable {

    @Inject
    Retrofit retrofit;

    private Product product;
    private SpinnerAdapter productCategoryAdapter;
    private SpinnerAdapter specializationAdapter;

    public NewProductViewModel(Context context) {
        MyApplication.appComponent.inject(this);
        product = new Product();
        productCategoryAdapter = new ArrayAdapter<>(context, R.layout.spinner_list_item, R.id.spinner_item, ProductCategory.values());
        specializationAdapter = new ArrayAdapter<>(context, R.layout.spinner_list_item, R.id.spinner_item, Specialization.values());
    }


    public void tost(Context context) {
        Toast.makeText(context, product.toString(), Toast.LENGTH_SHORT).show();
    }
}

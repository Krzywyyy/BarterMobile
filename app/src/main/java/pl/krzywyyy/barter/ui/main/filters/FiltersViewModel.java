package pl.krzywyyy.barter.ui.main.filters;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import lombok.Getter;
import lombok.Setter;
import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.model.domain.Filters;
import pl.krzywyyy.barter.model.enums.ProductCategory;
import pl.krzywyyy.barter.model.enums.Specialization;
import pl.krzywyyy.barter.ui.main.home.HomeFragment;

@Getter
@Setter
public class FiltersViewModel extends BaseObservable {

    private SpinnerAdapter productCategoryAdapter;
    private SpinnerAdapter specializationAdapter;
    private Filters filters;

    FiltersViewModel(Context context) {
        productCategoryAdapter = new ArrayAdapter<>(context, R.layout.spinner_list_item, R.id.spinner_item, ProductCategory.values());
        specializationAdapter = new ArrayAdapter<>(context, R.layout.spinner_list_item, R.id.spinner_item, Specialization.values());
        filters = new Filters();
    }

    void search(Context context) {
        FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        Fragment home = new HomeFragment();

        Bundle args = new Bundle();
        args.putString("searchPhrase", filters.getSearchPhrase());
        args.putString("category", filters.getCategory().name());
        args.putString("specialization", filters.getSpecialization().name());
        args.putString("distance", filters.getDistance());
        home.setArguments(args);
        fragmentTransaction.replace(R.id.main_nav_host_fragment, home);
    }
}

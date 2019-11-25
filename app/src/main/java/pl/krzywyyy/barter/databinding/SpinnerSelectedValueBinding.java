package pl.krzywyyy.barter.databinding;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import java.util.Arrays;

import pl.krzywyyy.barter.model.enums.ProductCategory;
import pl.krzywyyy.barter.model.enums.Specialization;

public class SpinnerSelectedValueBinding {
    
    @BindingAdapter(
            value = {"bind:productCategory", "bind:productCategoryAttrChanged"},
            requireAll = false)
    public static void setProductCategory(Spinner spinner,
                                          ProductCategory selectedProductCategory,
                                          InverseBindingListener inverseBindingListener) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                inverseBindingListener.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                inverseBindingListener.onChange();
            }
        });
        spinner.setSelection(Arrays.asList(ProductCategory.values()).indexOf(selectedProductCategory));
    }

    @InverseBindingAdapter(
            attribute = "bind:productCategory",
            event = "bind:productCategoryAttrChanged")
    public static ProductCategory getProductCategory(Spinner spinner) {
        return (ProductCategory) spinner.getSelectedItem();
    }

    @BindingAdapter(
            value = {"bind:productSpecialization", "bind:productSpecializationAttrChanged"},
            requireAll = false)
    public static void setProductSpecialization(Spinner spinner,
                                                Specialization selectedProductSpecialization,
                                                InverseBindingListener inverseBindingListener) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                inverseBindingListener.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                inverseBindingListener.onChange();
            }
        });
        spinner.setSelection(Arrays.asList(Specialization.values()).indexOf(selectedProductSpecialization));
    }

    @InverseBindingAdapter(
            attribute = "bind:productSpecialization",
            event = "bind:productSpecializationAttrChanged")
    public static Specialization getProductSpecialization(Spinner spinner) {
        return (Specialization) spinner.getSelectedItem();
    }
}

package pl.krzywyyy.barter.databinding;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import java.util.Arrays;

import pl.krzywyyy.barter.model.enums.ProductCategory;
import pl.krzywyyy.barter.model.enums.Specialization;

class SpinnerBindings {

    @BindingAdapter(value = "android:entries")
    public static void setEntries(Spinner spinner, SpinnerAdapter spinnerAdapter) {
        spinner.setAdapter(spinnerAdapter);
    }

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

    @BindingAdapter(value = {"bind:productImage", "bind:productImageAttrChanged"},
            requireAll = false)
    public static void setProductImage(ImageView imageView,
                                       Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @InverseBindingAdapter(attribute = "bind:productImage",
            event = "bind:productImageAttrChanged")
    public static Bitmap getProductImage(ImageView imageView) {
        return ((BitmapDrawable) imageView.getDrawable()).getBitmap();
    }
}

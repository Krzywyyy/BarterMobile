package pl.krzywyyy.barter.databinding;

import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.databinding.BindingAdapter;

class SpinnerBindings {

    @BindingAdapter(value = "android:entries")
    public static void setEntries(Spinner spinner, SpinnerAdapter spinnerAdapter) {
        spinner.setAdapter(spinnerAdapter);
    }
}

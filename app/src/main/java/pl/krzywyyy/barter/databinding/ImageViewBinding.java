package pl.krzywyyy.barter.databinding;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

public class ImageViewBinding {

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

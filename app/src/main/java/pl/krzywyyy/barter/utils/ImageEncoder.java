package pl.krzywyyy.barter.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class ImageEncoder {
    public static Bitmap toImage(String base64) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}

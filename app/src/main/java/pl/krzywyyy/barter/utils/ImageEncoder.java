package pl.krzywyyy.barter.utils;


import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class ImageEncoder {
    public static String toBase64(Bitmap bitmap) {
        byte[] imageByte = getBytesFromBitmap(bitmap);
        return Base64.encodeToString(imageByte, Base64.DEFAULT);
    }

    private static byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}

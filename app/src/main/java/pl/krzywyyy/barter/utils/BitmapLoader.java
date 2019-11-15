package pl.krzywyyy.barter.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import androidx.exifinterface.media.ExifInterface;

import java.io.IOException;
import java.util.Objects;

public class BitmapLoader {

    public static Bitmap loadBitmapFromUri(Context context, Uri imageUri) throws IOException {
        return createBitmap(context, imageUri);
    }

    private static Bitmap createBitmap(Context context, Uri imageUri) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
        bitmap = rotateImageIfRequired(bitmap, Uri.parse(getAbsolutePath(context, imageUri)));
        return bitmap;
    }

    private static String getAbsolutePath(Context context, Uri uri) {
        String absolutePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        String id = wholeID.split(":")[1];
        String[] column = {MediaStore.Images.Media.DATA};

        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = Objects.requireNonNull(context.getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel, new String[]{id}, null));

        int columnIndex = Objects.requireNonNull(cursor).getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            absolutePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return absolutePath;
    }

    private static Bitmap rotateImageIfRequired(Bitmap bitmap, Uri imageUri) throws IOException {
        ExifInterface exif = new ExifInterface(imageUri.toString());

        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(bitmap, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(bitmap, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(bitmap, 270);
            default:
                return bitmap;
        }
    }

    private static Bitmap rotateImage(Bitmap bitmap, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}

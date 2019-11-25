package pl.krzywyyy.barter.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesManager {

    private static final String pref = "Authorization";

    public static void clearSharedPreferences(Context context) {
        context.getSharedPreferences(pref, MODE_PRIVATE).edit().clear().apply();
    }

    public static boolean containsToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(pref, MODE_PRIVATE);
        return sharedPreferences.contains(pref);
    }

    public static void saveToken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(pref, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(pref, token);
        editor.apply();
    }

    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(pref, MODE_PRIVATE);
        return sharedPreferences.getString(pref, null);
    }
}

package pl.krzywyyy.barter.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static final String pref = "Authorization";

    public static void saveTokenToPreferences(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(pref, token);
        editor.apply();
    }

    public static String getTokenFromPreferences(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        return sharedPreferences.getString(pref,null);
    }
}

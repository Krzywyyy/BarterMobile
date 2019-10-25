package pl.krzywyyy.barter.utils;

import android.content.Context;
import android.content.Intent;

public class ActivityChanger {

    public static void change(Context context, Class activity) {
        context.startActivity(new Intent(context, activity));
    }
}

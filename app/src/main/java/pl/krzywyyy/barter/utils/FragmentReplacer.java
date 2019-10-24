package pl.krzywyyy.barter.utils;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentReplacer {

    public static void replaceFragment(Context context, int placeholderId, Fragment fragment) {
        FragmentTransaction fragmentTransaction =
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(placeholderId, fragment);
        fragmentTransaction.commit();
    }
}

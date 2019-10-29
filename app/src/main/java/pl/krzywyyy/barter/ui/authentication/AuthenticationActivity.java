package pl.krzywyyy.barter.ui.authentication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.ui.main.MainActivity;
import pl.krzywyyy.barter.utils.ActivityChanger;
import pl.krzywyyy.barter.utils.FragmentReplacer;
import pl.krzywyyy.barter.utils.SharedPreferencesManager;
import pl.krzywyyy.barter.utils.TokenExplorator;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        Context context = getApplicationContext();

        if (SharedPreferencesManager.containsToken(context)) {
            if (TokenExplorator.isTokenExpired(context)) {
                removeTokenAndRedirectToLogin(context);
                return;
            } else {
                redirectToMainApp();
                return;
            }
        }
        showLoginFragment();
    }

    private void removeTokenAndRedirectToLogin(Context context) {
        Toast.makeText(context, getString(R.string.token_expired_toast), Toast.LENGTH_SHORT).show();
        SharedPreferencesManager.clearSharedPreferences(context);
        showLoginFragment();
    }

    private void redirectToMainApp() {
        ActivityChanger.change(this, MainActivity.class);
        finish();
    }

    private void showLoginFragment() {
        FragmentReplacer.replaceFragment(this, R.id.authentication_placeholder, new LoginFragment());
    }
}

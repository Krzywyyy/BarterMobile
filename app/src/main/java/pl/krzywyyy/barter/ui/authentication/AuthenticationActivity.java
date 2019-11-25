package pl.krzywyyy.barter.ui.authentication;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.ui.main.MainActivity;
import pl.krzywyyy.barter.utils.ActivityChanger;
import pl.krzywyyy.barter.utils.SharedPreferencesManager;
import pl.krzywyyy.barter.utils.TokenExplorator;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        AppBarConfiguration mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_login, R.id.nav_register)
                .build();

        NavigationView navigationView = findViewById(R.id.auth_nav_view);

        NavController navController = Navigation.findNavController(this, R.id.auth_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Context context = getApplicationContext();

        if (SharedPreferencesManager.containsToken(context)) {
            if (TokenExplorator.isTokenExpired(context)) {
                removeToken(context);
            } else {
                redirectToMainApp();
            }
        }
    }

    private void removeToken(Context context) {
        Toast.makeText(context, getString(R.string.token_expired_toast), Toast.LENGTH_SHORT).show();
        SharedPreferencesManager.clearSharedPreferences(context);
    }

    private void redirectToMainApp() {
        ActivityChanger.change(this, MainActivity.class);
        finish();
    }

}

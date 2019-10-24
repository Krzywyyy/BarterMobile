package pl.krzywyyy.barter.authentication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.main.MainActivity;
import pl.krzywyyy.barter.utils.FragmentReplacer;
import pl.krzywyyy.barter.utils.SharedPreferencesManager;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        if (SharedPreferencesManager.getTokenFromPreferences(getApplicationContext()) != null)
            FragmentReplacer.replaceFragment(this, R.id.authentication_placeholder, new LoginFragment());
        else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}

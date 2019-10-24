package pl.krzywyyy.barter.authenticationActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.utils.FragmentReplacer;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        FragmentReplacer.replaceFragment(this, R.id.authentication_placeholder, new LoginFragment());
    }
}

package pl.krzywyyy.barter.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.ui.authentication.AuthenticationActivity;
import pl.krzywyyy.barter.utils.ActivityChanger;
import pl.krzywyyy.barter.utils.SharedPreferencesManager;
import pl.krzywyyy.barter.utils.TokenExplorator;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.getMenu().findItem(R.id.sign_out_button).setOnMenuItemClickListener(e -> signOut());

        setUserDetailsInMenu(navigationView);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    private boolean signOut() {
        SharedPreferencesManager.clearSharedPreferences(getApplicationContext());
        ActivityChanger.change(this, AuthenticationActivity.class);
        Toast.makeText(this, getString(R.string.successful_sign_out), Toast.LENGTH_SHORT).show();
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_app_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void setUserDetailsInMenu(NavigationView navigationView) {
        View navHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_main);

        TextView userName = navHeaderView.findViewById(R.id.menu_user_name);
        userName.setText(TokenExplorator.getUserNameFromToken(getApplicationContext()));

        TextView userEmail = navHeaderView.findViewById(R.id.menu_user_email);
        userEmail.setText(TokenExplorator.getUserEmailFromToken(getApplicationContext()));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}

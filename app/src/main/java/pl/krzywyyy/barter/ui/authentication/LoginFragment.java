package pl.krzywyyy.barter.ui.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.net.HttpURLConnection;
import java.util.Objects;

import javax.inject.Inject;

import pl.krzywyyy.barter.MyApplication;
import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.api.UserInterface;
import pl.krzywyyy.barter.model.domain.User;
import pl.krzywyyy.barter.ui.main.MainActivity;
import pl.krzywyyy.barter.utils.ActivityChanger;
import pl.krzywyyy.barter.utils.SharedPreferencesManager;
import pl.krzywyyy.barter.utils.TokenExplorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.internal.EverythingIsNonNull;

public class LoginFragment extends Fragment {

    private final String AUTHORIZATION_HEADER = "Authorization";

    @Inject
    Retrofit retrofit;

    private EditText emailInput;
    private EditText passwordInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        MyApplication.appComponent.inject(this);

        assignItems(view);

        return view;
    }

    private void assignItems(View view) {
        emailInput = view.findViewById(R.id.email);
        passwordInput = view.findViewById(R.id.password);
        Button signInButton = view.findViewById(R.id.sign_in_button);
        TextView signUp = view.findViewById(R.id.nav_register);

        setButtonsListeners(signInButton, signUp);
    }

    private void setButtonsListeners(Button signInButton, TextView signUp) {
        signInButton.setOnClickListener(e -> signIn());
        signUp.setOnClickListener(this::signUp);
    }

    private void signIn() {
        User user = createUserWithLoginCredentials();

        UserInterface userService = retrofit.create(UserInterface.class);

        Call<Void> call = userService.signIn(user);

        call.enqueue(new Callback<Void>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call call, Response response) {
                if (response.code() == HttpURLConnection.HTTP_NO_CONTENT) {
                    successfulAuthentication(response);
                    ActivityChanger.change(Objects.requireNonNull(getContext()), MainActivity.class);
                } else {
                    failedAuthentication();
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), getString(R.string.failed_retrofit_request_toast), Toast.LENGTH_LONG).show();
            }
        });
    }

    private User createUserWithLoginCredentials() {
        User user = new User();
        user.setEmail(String.valueOf(emailInput.getText()));
        user.setPassword(String.valueOf(passwordInput.getText()));
        return user;
    }

    private void successfulAuthentication(Response response) {
        String authorization = response.headers().get(AUTHORIZATION_HEADER);
        SharedPreferencesManager.saveToken(Objects.requireNonNull(getContext()), authorization);
        String userName = TokenExplorator.getUserNameFromToken(getContext());
        showWelcomeMessage(userName);
    }

    private void failedAuthentication() {
        Toast.makeText(getContext(), getString(R.string.wrong_email_or_password_toast), Toast.LENGTH_SHORT).show();
        passwordInput.setText("");
    }

    private void signUp(View view) {
        NavDirections action = LoginFragmentDirections.actionNavLoginToNavRegister();
        Navigation.findNavController(view).navigate(action);
    }

    private void showWelcomeMessage(String userName) {
        Toast.makeText(getContext(), String.format(getString(R.string.welcome_toast), userName), Toast.LENGTH_SHORT).show();
    }
}

package pl.krzywyyy.barter.ui.authentication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.net.HttpURLConnection;

import javax.inject.Inject;

import pl.krzywyyy.barter.MyApplication;
import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.api.barter.UserInterface;
import pl.krzywyyy.barter.model.domain.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.internal.EverythingIsNonNull;

public class RegisterFragment extends Fragment {

    @Inject
    Retrofit retrofit;

    private EditText emailInput;
    private EditText passwordInput;
    private EditText repasswordInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        MyApplication.appComponent.inject(this);

        assignItems(view);

        return view;
    }

    private void assignItems(View view) {
        emailInput = view.findViewById(R.id.new_user_email);
        passwordInput = view.findViewById(R.id.new_user_password);
        repasswordInput = view.findViewById(R.id.new_user_repassword);

        Button backToLoginButton = view.findViewById(R.id.back_to_login_button);
        Button signUpButton = view.findViewById(R.id.sign_up_button);

        backToLoginButton.setOnClickListener(this::backToLoginFragment);
        signUpButton.setOnClickListener(this::signUp);
    }

    private void backToLoginFragment(View view) {
        NavDirections action = RegisterFragmentDirections.actionNavRegisterToNavLogin();
        Navigation.findNavController(view).navigate(action);
    }

    private void signUp(View view) {
        if (!checkIfPasswordsMatches()) {
            Toast.makeText(getContext(), getString(R.string.not_matching_passwords_toast), Toast.LENGTH_SHORT).show();
            return;
        }

        User registrationUser = createUserWithRegistrationCredentials();

        registerNewUser(view, registrationUser);
    }

    private boolean checkIfPasswordsMatches() {
        return String.valueOf(passwordInput.getText()).equals(String.valueOf(repasswordInput.getText()));
    }

    private User createUserWithRegistrationCredentials() {
        User registrationUser = new User();
        registrationUser.setEmail(String.valueOf(emailInput.getText()));
        registrationUser.setPassword(String.valueOf(passwordInput.getText()));
        return registrationUser;
    }

    private void registerNewUser(View view, User registrationUser) {
        UserInterface userService = retrofit.create(UserInterface.class);

        Call<Void> call = userService.signUp(registrationUser);

        call.enqueue(new Callback<Void>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    backToLoginFragment(view);
                    Toast.makeText(getContext(), getString(R.string.successful_registration_toast), Toast.LENGTH_SHORT).show();
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), getString(R.string.failed_retrofit_request_toast), Toast.LENGTH_LONG).show();
            }
        });
    }
}

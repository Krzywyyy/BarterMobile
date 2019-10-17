package pl.krzywyyy.barter.login;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import pl.krzywyyy.barter.R;

public class LoginFragment extends Fragment {

    private EditText emailInput;
    private EditText passwordInput;
    private Button signInButton;
    private TextView signUp;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        assignItems(view);

        return view;
    }

    private void assignItems(View view) {
        emailInput = view.findViewById(R.id.email);
        passwordInput = view.findViewById(R.id.password);
        signInButton = view.findViewById(R.id.sign_in_button);
        signUp = view.findViewById(R.id.sign_up);

        signInButton.setOnClickListener(e -> signIn());
        signUp.setOnClickListener(e -> signUp());
    }

    private void signUp() {
        try {
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.authentication_placeholder, new RegisterFragment());
            fragmentTransaction.commit();
        } catch(NullPointerException npe){
            Toast.makeText(getContext(), npe.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void signIn() {
        Toast.makeText(getContext(), emailInput.getText().toString() + passwordInput.getText().toString(), Toast.LENGTH_LONG).show();
    }

}

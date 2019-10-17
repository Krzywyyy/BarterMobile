package pl.krzywyyy.barter.login;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pl.krzywyyy.barter.R;

public class RegisterFragment extends Fragment {

    private Button backToLoginButton;
    private Button signUpButton;

    public RegisterFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        assignItems(view);

        return view;
    }

    private void assignItems(View view){
        backToLoginButton = view.findViewById(R.id.back_to_login_button);
        signUpButton = view.findViewById(R.id.sign_up_button);

        backToLoginButton.setOnClickListener(e -> backToLoginFragment());
    }

    private void backToLoginFragment() {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.authentication_placeholder, new LoginFragment());
        fragmentTransaction.commit();
    }
}

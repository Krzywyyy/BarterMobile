package pl.krzywyyy.barter.login;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.utils.FragmentReplacer;

public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        assignItems(view);

        return view;
    }

    private void assignItems(View view) {
        Button backToLoginButton = view.findViewById(R.id.back_to_login_button);
        Button signUpButton = view.findViewById(R.id.sign_up_button);

        backToLoginButton.setOnClickListener(e -> backToLoginFragment());
    }

    private void backToLoginFragment() {
        FragmentReplacer.replaceFragment(getContext(), R.id.authentication_placeholder, new LoginFragment());
    }
}

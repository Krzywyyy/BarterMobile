package pl.krzywyyy.barter.ui.main.secondFragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.krzywyyy.barter.R;

public class SecondFragment extends Fragment {

    private SecondViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(SecondViewModel.class);
        View root = inflater.inflate(R.layout.fragment_second, container, false);
        return root;
    }
}

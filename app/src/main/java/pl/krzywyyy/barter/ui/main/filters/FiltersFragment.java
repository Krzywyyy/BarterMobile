package pl.krzywyyy.barter.ui.main.filters;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.databinding.FragmentFiltersBinding;

public class FiltersFragment extends DialogFragment {

    private FiltersViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewModel = new FiltersViewModel(getContext());
        FragmentFiltersBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_filters,
                container,
                false
        );
        binding.setFiltersViewModel(mViewModel);

        View view = binding.getRoot();
        view.findViewById(R.id.search_with_filters).setOnClickListener(e -> mViewModel.search(getContext()));

        return view;
    }
}

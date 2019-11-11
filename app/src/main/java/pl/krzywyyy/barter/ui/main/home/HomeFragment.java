package pl.krzywyyy.barter.ui.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.model.domain.ProductView;

public class HomeFragment extends Fragment {

    private List<ProductView> products = new ArrayList<>();
    private HomeViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private HomeAdapter productsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mRecyclerView = view.findViewById(R.id.all_products_recycler_view);

        productsAdapter = new HomeAdapter(products, getContext());
        mRecyclerView.setAdapter(productsAdapter);

        mViewModel.getProductViews().observe(this, productViews -> {
            products = productViews;

            productsAdapter = new HomeAdapter(products, getContext());
            mRecyclerView.setAdapter(productsAdapter);
        });

        return view;
    }

}

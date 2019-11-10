package pl.krzywyyy.barter.ui.main.userproducts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.model.domain.ProductView;
import pl.krzywyyy.barter.model.domain.User;

public class UserProductsFragment extends Fragment {

    private List<ProductView> products = new ArrayList<>();
    private UserProductsViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private UserProductsAdapter productsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_products, container, false);
        mRecyclerView = view.findViewById(R.id.users_products_recycler_view);

        mViewModel = ViewModelProviders.of(this).get(UserProductsViewModel.class);

        productsAdapter = new UserProductsAdapter(products);

        mViewModel.getProductViews().observe(this, productViews -> {
            products = productViews;

            productsAdapter = new UserProductsAdapter(products);
            mRecyclerView.setAdapter(productsAdapter);
            Toast.makeText(getContext(), "Zmiana: " + productViews.size(), Toast.LENGTH_SHORT).show();
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(productsAdapter);

        return view;
    }

}

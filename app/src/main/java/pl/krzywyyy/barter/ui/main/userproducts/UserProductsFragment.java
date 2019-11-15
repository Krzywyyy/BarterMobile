package pl.krzywyyy.barter.ui.main.userproducts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.model.domain.ProductView;
import pl.krzywyyy.barter.ui.main.newproduct.NewProductFragment;

public class UserProductsFragment extends Fragment {

    private List<ProductView> products = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private UserProductsAdapter productsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_products, container, false);
        UserProductsViewModel mViewModel = ViewModelProviders.of(this).get(UserProductsViewModel.class);
        mRecyclerView = view.findViewById(R.id.users_products_recycler_view);

        productsAdapter = new UserProductsAdapter(products, getContext());
        mRecyclerView.setAdapter(productsAdapter);

        mViewModel.getProductViews().observe(this, productViews -> {
            products = productViews;

            productsAdapter = new UserProductsAdapter(products, getContext());
            mRecyclerView.setAdapter(productsAdapter);
        });

        FloatingActionButton fab = view.findViewById(R.id.add_product_fab);
        fab.setOnClickListener(e -> showAddNewProductDialog());

        return view;
    }

    private void showAddNewProductDialog() {
        FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
        String newProductDialogFragmentName = "newProductDialog";
        fragmentTransaction.addToBackStack(newProductDialogFragmentName);
        DialogFragment newProductDialogFragment = new NewProductFragment();
        newProductDialogFragment.show(fragmentTransaction, newProductDialogFragmentName);
    }

}

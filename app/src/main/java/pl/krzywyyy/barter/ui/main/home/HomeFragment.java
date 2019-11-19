package pl.krzywyyy.barter.ui.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.model.domain.ProductView;

public class HomeFragment extends Fragment {

    private List<ProductView> products = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private HomeAdapter productsAdapter;
    private int page;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        HomeViewModel mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mRecyclerView = view.findViewById(R.id.all_products_recycler_view);

        page = 2;

        productsAdapter = new HomeAdapter(products, getContext());
        mRecyclerView.setAdapter(productsAdapter);

        mViewModel.getProductViews().observe(this, productViews -> {
            products = productViews;

            productsAdapter = new HomeAdapter(products, getContext());
            mRecyclerView.setAdapter(productsAdapter);
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!mRecyclerView.canScrollVertically(1)) {
                    mViewModel.loadNextProducts(page);
                    mViewModel.getNewProducts().observe(
                            Objects.requireNonNull(getActivity()), productViews -> {
                                page++;
                                productsAdapter.addItems(productViews);
                            });
                }
            }
        });
        return view;
    }
}

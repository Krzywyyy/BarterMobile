package pl.krzywyyy.barter.ui.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.model.domain.ProductView;
import pl.krzywyyy.barter.model.enums.ProductCategory;
import pl.krzywyyy.barter.model.enums.Specialization;
import pl.krzywyyy.barter.ui.main.filters.FiltersFragment;
import pl.krzywyyy.barter.ui.main.newproduct.NewProductFragment;

public class HomeFragment extends Fragment {

    private List<ProductView> products = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private HomeAdapter productsAdapter;
    private int page;
    private FloatingActionButton fab;

    private String searchPhrase;
    private String category;
    private String specialization;
    private Integer distance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = view.findViewById(R.id.all_products_recycler_view);

        fab = view.findViewById(R.id.filter_products);
        fab.setOnClickListener(e -> openFilters());

        getFilters();
        distance = 1000000;
        HomeViewModel mViewModel = new HomeViewModel(searchPhrase, category, specialization, distance, 0f, 0f);

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
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
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

    private void getFilters() {
        if (getArguments() != null) {
            String searchPhrase = getArguments().getString("searchPhrase");
            String categoryName = getArguments().getString("category");
            String specializationName = getArguments().getString("specialization");
            String distance = getArguments().getString("distance");

            this.searchPhrase = searchPhrase != null ? searchPhrase : "";
            this.category = ProductCategory.valueOf(categoryName) != ProductCategory.ALL ? categoryName : "";
            this.specialization = Specialization.valueOf(specializationName) != Specialization.ALL ? specializationName : "";
            this.distance = distance != null ? Integer.parseInt(distance) : 1000000;
        }
    }

    private void openFilters() {
        FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
        String filtersDialog = "filtersDialog";
        fragmentTransaction.addToBackStack(filtersDialog);
        DialogFragment filtersFragment = new FiltersFragment();
        filtersFragment.show(fragmentTransaction, filtersDialog);
    }
}

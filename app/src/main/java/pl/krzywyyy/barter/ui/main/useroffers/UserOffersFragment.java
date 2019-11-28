package pl.krzywyyy.barter.ui.main.useroffers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.model.domain.Offer;

public class UserOffersFragment extends Fragment {

    private List<Offer> userOffers = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private UserOffersAdapter offersAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_offers, container, false);
        UserOffersViewModel mViewModel = ViewModelProviders.of(this).get(UserOffersViewModel.class);
        mRecyclerView = view.findViewById(R.id.users_offers_recycler_view);

        offersAdapter = new UserOffersAdapter(userOffers);
        mRecyclerView.setAdapter(offersAdapter);

        mViewModel.getUserOffers().observe(this, offers -> {
            userOffers = offers;

            offersAdapter = new UserOffersAdapter(userOffers);
            mRecyclerView.setAdapter(offersAdapter);
        });

        return view;
    }
}

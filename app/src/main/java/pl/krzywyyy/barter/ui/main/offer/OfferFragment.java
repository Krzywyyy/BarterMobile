package pl.krzywyyy.barter.ui.main.offer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.databinding.FragmentOfferBinding;

public class OfferFragment extends DialogFragment {

    private OfferViewModel mViewModel;
    private int productId;
    private String title;

    public OfferFragment(int productId, String title) {
        this.productId = productId;
        this.title = title;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new OfferViewModel();

        FragmentOfferBinding fragmentOfferBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_offer,
                container,
                false);
        fragmentOfferBinding.setOfferViewModel(mViewModel);

        View view = fragmentOfferBinding.getRoot();

        view.findViewById(R.id.cancel_offer_button)
                .setOnClickListener(e -> mViewModel.cancel(getContext(), this));
        view.findViewById(R.id.confirm_offer_button)
                .setOnClickListener(e -> mViewModel.makeOffer(getContext(), productId, title));

        mViewModel.getOfferDone().observe(this, offerDone -> {
            if (offerDone) {
                this.dismiss();
            }
        });
        return view;
    }
}

package pl.krzywyyy.barter.ui.main.useroffers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.model.domain.Offer;

public class UserOffersAdapter extends RecyclerView.Adapter<UserOffersViewHolder> {

    private List<Offer> userOffers;

    public UserOffersAdapter(List<Offer> userOffers) {
        this.userOffers = userOffers;
    }

    @NonNull
    @Override
    public UserOffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_offer_card_view, parent, false);
        return new UserOffersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserOffersViewHolder holder, int position) {
        if (userOffers != null) {
            holder.offerTitle.setText(userOffers.get(position).getTitle());
            holder.offerMessage.setText(userOffers.get(position).getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return userOffers != null ? userOffers.size() : 0;
    }
}

package pl.krzywyyy.barter.ui.main.useroffers;

import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
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
            String message = "Złożona oferta: " + userOffers.get(position).getMessage();
            String product = "Produkt: " + userOffers.get(position).getTitle();

            final SpannableStringBuilder messageSB = new SpannableStringBuilder(message);
            final SpannableStringBuilder productSB = new SpannableStringBuilder(product);

            final StyleSpan bold = new StyleSpan(Typeface.BOLD);
            final StyleSpan italic = new StyleSpan(Typeface.NORMAL);

            messageSB.setSpan(bold, 0, 15, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            messageSB.setSpan(italic, 15, messageSB.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            productSB.setSpan(bold, 0, 8, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            productSB.setSpan(italic, 8, productSB.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            holder.offerTitle.setText(productSB);
            holder.offerMessage.setText(messageSB);
        }
    }

    @Override
    public int getItemCount() {
        return userOffers != null ? userOffers.size() : 0;
    }
}

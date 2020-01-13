package pl.krzywyyy.barter.ui.main.productdetails.productoffers;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.krzywyyy.barter.R;

class ProductOffersViewHolder extends RecyclerView.ViewHolder {
        TextView offerTitle;
        TextView offerMessage;

        ProductOffersViewHolder(@NonNull View itemView) {
        super(itemView);
        offerTitle = itemView.findViewById(R.id.product_offer_title_card_view);
        offerMessage = itemView.findViewById(R.id.product_offer_message_card_view);
        }
}

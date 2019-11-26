package pl.krzywyyy.barter.ui.main.useroffers;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.krzywyyy.barter.R;

class UserOffersViewHolder extends RecyclerView.ViewHolder {
    TextView offerTitle;
    TextView offerMessage;

    UserOffersViewHolder(@NonNull View itemView) {
        super(itemView);
        offerTitle = itemView.findViewById(R.id.offer_title_card_view);
        offerMessage = itemView.findViewById(R.id.offer_message_card_view);
    }
}

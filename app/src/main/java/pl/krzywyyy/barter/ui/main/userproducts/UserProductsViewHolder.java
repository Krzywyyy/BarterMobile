package pl.krzywyyy.barter.ui.main.userproducts;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.krzywyyy.barter.R;

class UserProductsViewHolder extends RecyclerView.ViewHolder  {
    TextView productTitle;
    ImageView productImage;

    UserProductsViewHolder(@NonNull View itemView) {
        super(itemView);
        productTitle = itemView.findViewById(R.id.product_title_card_view);
        productImage = itemView.findViewById(R.id.product_image_card_view);
    }
}

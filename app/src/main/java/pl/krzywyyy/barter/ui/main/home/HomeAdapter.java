package pl.krzywyyy.barter.ui.main.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.model.domain.ProductView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<ProductView> productViewList;

    public HomeAdapter(List<ProductView> productViews) {
        this.productViewList = productViews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (productViewList != null) {
            holder.productTitle.setText(productViewList.get(position).getTitle());
            holder.productImage.setImageBitmap(productViewList.get(position).getImage());
            holder.itemView.setOnClickListener(e -> Toast.makeText(e.getContext(), "Works", Toast.LENGTH_SHORT).show());
        }
    }

    @Override
    public int getItemCount() {
        return productViewList != null ? productViewList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView productTitle;
        ImageView productImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            productTitle = itemView.findViewById(R.id.product_title_card_view);
            productImage = itemView.findViewById(R.id.product_image_card_view);
        }
    }
}

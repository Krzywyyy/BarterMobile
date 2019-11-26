package pl.krzywyyy.barter.ui.main.userproducts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.model.domain.ProductView;
import pl.krzywyyy.barter.ui.main.productdetails.ProductDetailsFragment;

public class UserProductsAdapter extends RecyclerView.Adapter<UserProductsViewHolder> {

    private final Context context;
    private final String dialogFragmentName = "detailDialog";
    private List<ProductView> productViewList;

    public UserProductsAdapter(List<ProductView> productViews, Context context) {
        this.productViewList = productViews;
        this.context = context;
    }

    @NonNull
    @Override
    public UserProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_product_card_view, parent, false);
        return new UserProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserProductsViewHolder holder, int position) {
        if (productViewList != null) {
            holder.productTitle.setText(productViewList.get(position).getTitle());
            holder.productImage.setImageBitmap(productViewList.get(position).getImage());
            holder.itemView.setOnClickListener(e -> {
                FragmentTransaction fragmentTransaction = ((AppCompatActivity) context)
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(dialogFragmentName);
                DialogFragment productDetailsDialogFragment = new ProductDetailsFragment(productViewList.get(position).getId());
                productDetailsDialogFragment.show(fragmentTransaction, dialogFragmentName);
            });
        }
    }

    @Override
    public int getItemCount() {
        return productViewList != null ? productViewList.size() : 0;
    }
}

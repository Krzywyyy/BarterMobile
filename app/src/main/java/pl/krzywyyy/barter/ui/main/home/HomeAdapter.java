package pl.krzywyyy.barter.ui.main.home;

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

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    private final Context context;
    private final String dialogFragmentName = "detailDialog";
    private List<ProductView> productViewList;

    HomeAdapter(List<ProductView> productViews, Context context) {
        this.productViewList = productViews;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_view, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        if (productViewList != null) {
            holder.productTitle.setText(productViewList.get(position).getTitle());
            holder.productImage.setImageBitmap(productViewList.get(position).getImage());
            holder.itemView.setOnClickListener(e -> {
                showProductDetailDialog(position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return productViewList != null ? productViewList.size() : 0;
    }

    void addItems(List<ProductView> productViewList) {
        this.productViewList.addAll(productViewList);
        notifyDataSetChanged();
    }

    private void showProductDetailDialog(int position) {
        FragmentTransaction fragmentTransaction = ((AppCompatActivity) context)
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(dialogFragmentName);
        DialogFragment productDetailsDialogFragment = new ProductDetailsFragment(productViewList.get(position).getId());
        productDetailsDialogFragment.show(fragmentTransaction, dialogFragmentName);
    }
}

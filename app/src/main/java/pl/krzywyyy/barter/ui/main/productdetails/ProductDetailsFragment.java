package pl.krzywyyy.barter.ui.main.productdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import pl.krzywyyy.barter.R;

public class ProductDetailsFragment extends DialogFragment {

    private ImageView productImage;
    private TextView productTitle;
    private TextView productDescription;
    private TextView productSpecialization;
    private int productId;

    public ProductDetailsFragment(int productId) {
        this.productId = productId;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        view.findViewById(R.id.exit_detail_dialog).setOnClickListener(e -> this.dismiss());

        ProductDetailsViewModel mViewModel = new ProductDetailsViewModel(productId);
        productImage = view.findViewById(R.id.product_detail_image);
        productTitle = view.findViewById(R.id.product_detail_title);
        productDescription = view.findViewById(R.id.product_detail_description);
        productSpecialization = view.findViewById(R.id.product_detail_specialization);

        mViewModel.getProductDetail().observe(this, productDetail -> {
            productImage.setImageBitmap(productDetail.getImage());
            productTitle.setText(productDetail.getTitle());
            productDescription.setText(productDetail.getDescription());
            productSpecialization.setText(String.valueOf(productDetail.getSpecialization()));
        });
        return view;
    }
}

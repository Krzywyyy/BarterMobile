package pl.krzywyyy.barter.ui.main.productdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.ui.main.offer.OfferFragment;
import pl.krzywyyy.barter.ui.main.userproducts.UserProductsFragment;

public class ProductDetailsFragment extends DialogFragment {

    private ImageView productImage;
    private TextView productTitle;
    private TextView productDescription;
    private TextView productSpecialization;
    private TextView productAddress;
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

        Button makeAnOfferButton = view.findViewById(R.id.make_an_offer_button);
        makeAnOfferButton.setOnClickListener(e -> showOfferDialog());

        Button deleteProductButton = view.findViewById(R.id.delete_product_button);
        deleteProductButton.setOnClickListener(e -> mViewModel.removeProduct(getContext(), productId));

        setButton(makeAnOfferButton, deleteProductButton);

        productImage = view.findViewById(R.id.product_detail_image);
        productTitle = view.findViewById(R.id.product_detail_title);
        productDescription = view.findViewById(R.id.product_detail_description);
        productSpecialization = view.findViewById(R.id.product_detail_specialization);
        productAddress = view.findViewById(R.id.product_detail_address);

        mViewModel.getProductDetail().observe(this, productDetail -> {
            productImage.setImageBitmap(productDetail.getImage());
            productTitle.setText(productDetail.getTitle());
            productDescription.setText(productDetail.getDescription());
            productSpecialization.setText(String.valueOf(productDetail.getSpecialization()));
            productAddress.setText(productDetail.getAddress());
        });
        return view;
    }

    private void setButton(Button makeAnOfferButton, Button deleteProductButton) {
        if (getArguments() != null) {
            String parentFragment = getArguments().getString("parent");
            if (parentFragment != null) {
                if (parentFragment.equals(UserProductsFragment.class.getName())) {
                    deleteProductButton.setVisibility(View.VISIBLE);
                } else {
                    makeAnOfferButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void showOfferDialog() {
        FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
        String offerDialogFragmentName = "offerDialog";
        fragmentTransaction.addToBackStack(offerDialogFragmentName);
        DialogFragment offerFragment = new OfferFragment(productId, productTitle.getText().toString());
        offerFragment.show(fragmentTransaction, offerDialogFragmentName);
    }
}

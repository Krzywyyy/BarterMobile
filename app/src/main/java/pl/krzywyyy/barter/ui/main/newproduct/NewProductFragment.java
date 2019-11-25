package pl.krzywyyy.barter.ui.main.newproduct;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import java.io.IOException;
import java.util.Objects;

import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.databinding.FragmentNewProductBinding;
import pl.krzywyyy.barter.utils.BitmapLoader;

public class NewProductFragment extends DialogFragment {

    private final int RESULT_GALLERY = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        NewProductViewModel mViewModel = new NewProductViewModel(getContext());

        FragmentNewProductBinding fragmentNewProductBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_product, container, false);
        checkIfReadExternalPermissionIsGranted();
        fragmentNewProductBinding.setNewProductViewModel(mViewModel);

        fragmentNewProductBinding.getRoot().findViewById(R.id.new_product_image_button)
                .setOnClickListener(e -> loadImageFromGallery());
        fragmentNewProductBinding.getRoot().findViewById(R.id.exit_new_product_dialog)
                .setOnClickListener(e -> this.dismiss());
        fragmentNewProductBinding.getRoot().findViewById(R.id.add_new_product_button)
                .setOnClickListener(e -> mViewModel.addNewProduct(getContext()));

        return fragmentNewProductBinding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RESULT_GALLERY) {
            if (data != null) {
                Uri imageUri = data.getData();
                if (imageUri != null) {
                    try {
                        Bitmap bitmap = BitmapLoader.loadBitmapFromUri(getContext(), imageUri);
                        ImageView productImage = Objects.requireNonNull(getView()).findViewById(R.id.new_product_image);
                        productImage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Toast.makeText(getContext(), R.string.cannot_read_image, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private void checkIfReadExternalPermissionIsGranted() {
        if (ActivityCompat.checkSelfPermission(
                Objects.requireNonNull(getContext()), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RESULT_GALLERY);
        }
    }

    private void loadImageFromGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, RESULT_GALLERY);
    }
}

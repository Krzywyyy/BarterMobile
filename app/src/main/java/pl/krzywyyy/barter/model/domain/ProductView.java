package pl.krzywyyy.barter.model.domain;

import android.graphics.Bitmap;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductView {
    private int id;
    private String title;
    private Bitmap image;
}

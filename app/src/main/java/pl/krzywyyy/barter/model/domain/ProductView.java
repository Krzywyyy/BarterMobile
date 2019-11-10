package pl.krzywyyy.barter.model.domain;

import android.graphics.Bitmap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductView {
    private String title;
    private Bitmap image;
}

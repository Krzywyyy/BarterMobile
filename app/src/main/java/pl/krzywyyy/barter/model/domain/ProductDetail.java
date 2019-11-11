package pl.krzywyyy.barter.model.domain;

import android.graphics.Bitmap;

import lombok.Data;
import pl.krzywyyy.barter.model.enums.Specialization;

@Data
public class ProductDetail {
    private String title;
    private String description;
    private Specialization specialization;
    private Bitmap image;
}

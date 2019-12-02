package pl.krzywyyy.barter.model.domain;

import lombok.Data;
import pl.krzywyyy.barter.model.enums.ProductCategory;
import pl.krzywyyy.barter.model.enums.Specialization;

@Data
public class Product {
    private int id;
    private String title;
    private String description;
    private String image;
    private String address;
    private float latitude;
    private float longitude;
    private ProductCategory category;
    private Specialization specialization;
    private int userId;
}

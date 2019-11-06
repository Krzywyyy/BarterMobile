package pl.krzywyyy.barter.model.domain;

import lombok.Data;
import pl.krzywyyy.barter.model.enums.ProductCategories;
import pl.krzywyyy.barter.model.enums.Specializations;

@Data
public class Product {
    private int id;
    private String title;
    private String description;
    private String image;
    private ProductCategories category;
    private Specializations specialization;
    private int userId;
}

package pl.krzywyyy.barter.model.domain;

import lombok.Data;
import pl.krzywyyy.barter.model.enums.ProductCategory;
import pl.krzywyyy.barter.model.enums.Specialization;

@Data
public class Filters {
    private String searchPhrase;
    private ProductCategory category;
    private Specialization specialization;
    private String distance;
}

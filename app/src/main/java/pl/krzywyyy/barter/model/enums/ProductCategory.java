package pl.krzywyyy.barter.model.enums;

import androidx.annotation.NonNull;

import pl.krzywyyy.barter.model.dictionaries.ProductCategoryDictionary;

public enum ProductCategory {
    ALL(ProductCategoryDictionary.ALL),
    BOOK(ProductCategoryDictionary.BOOK),
    NOTES(ProductCategoryDictionary.NOTES),
    COACHING(ProductCategoryDictionary.COACHING),
    OTHER(ProductCategoryDictionary.OTHER);

    private String value;

    ProductCategory(String value){
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }
}

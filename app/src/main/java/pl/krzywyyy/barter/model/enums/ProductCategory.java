package pl.krzywyyy.barter.model.enums;

import androidx.annotation.NonNull;

import pl.krzywyyy.barter.model.dictionaries.ProductCategoryDictionary;

public enum ProductCategory {
    BOOK(ProductCategoryDictionary.BOOK),
    NOTES(ProductCategoryDictionary.NOTES),
    COACHING(ProductCategoryDictionary.COACHING),
    OTHER(ProductCategoryDictionary.OTHERS);

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

package pl.krzywyyy.barter.model.enums;

import androidx.annotation.NonNull;

import pl.krzywyyy.barter.model.dictionaries.SpecializationDictionary;

public enum Specialization {
    IT(SpecializationDictionary.IT),
    MECHANICS(SpecializationDictionary.MECHANICS),
    MECHATRONICS(SpecializationDictionary.MECHATRONICS),
    CHEMISTRY(SpecializationDictionary.CHEMISTRY),
    BIOLOGY(SpecializationDictionary.BIOLOGY),
    OTHER(SpecializationDictionary.OTHER);

    private String value;

    Specialization(String value){
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }
}

package pl.krzywyyy.barter.model.maps;

import lombok.Data;

@Data
public class Results {
    private String formatted_address;
    private Geometry geometry;
}
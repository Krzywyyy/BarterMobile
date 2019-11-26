package pl.krzywyyy.barter.model.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Offer {
    private int id;
    private String title;
    private String message;
    private int productId;
    private int offererId;
    private Date offerDate;
    private Date confirmDate;
}

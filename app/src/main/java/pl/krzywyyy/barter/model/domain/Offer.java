package pl.krzywyyy.barter.model.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Offer {
    private int id;
    private int offeredProductId;
    private int aimedProductId;
    private Date offerDate;
    private Date confirmDate;
}

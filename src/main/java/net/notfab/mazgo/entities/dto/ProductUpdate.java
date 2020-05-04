package net.notfab.mazgo.entities.dto;

import lombok.Data;

@Data
public class ProductUpdate {

    private String identifier;
    private String image;
    private int quantity = 0;

}

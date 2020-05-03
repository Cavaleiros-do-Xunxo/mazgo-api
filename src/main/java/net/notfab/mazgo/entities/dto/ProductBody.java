package net.notfab.mazgo.entities.dto;

import lombok.Data;

@Data
public class ProductBody {

    private String name;
    private String identifier;
    private int quantity = 0;

}

package net.notfab.mazgo.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Product {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;
    private int quantity = 0;
    private String identifier; // Google Vision
    private String image;

}

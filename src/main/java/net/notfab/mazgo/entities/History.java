package net.notfab.mazgo.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@Entity
public class History {

    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    private Product product;

    private int quantity = 0;
    private HistoryAction action;
    private String image;
    private long timestamp = System.currentTimeMillis();

}

package net.notfab.mazgo.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class History {

    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    private Product product;

    @Enumerated(EnumType.STRING)
    private HistoryAction action;

    private int quantity = 0;
    private String image;
    private long timestamp = System.currentTimeMillis();

}

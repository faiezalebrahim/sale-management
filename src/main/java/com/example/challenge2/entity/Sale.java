package com.example.challenge2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {
    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Getter
    @Setter
    @Column
    private String seller;

    @Getter
    @Setter
    @Column
    private Integer quantity;

    @Getter
    @Setter
    @Column
    private Double price;

    public Double getTotalPrice() {
        if (quantity != null && price != null) {
            return quantity * price;
        }
        return null;
    }
}

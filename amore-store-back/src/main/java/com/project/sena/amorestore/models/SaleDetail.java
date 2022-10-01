package com.project.sena.amorestore.models;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "saleDetails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Sale sale;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Product product;

    private long amount;

    private double price;

    private double discount;


}

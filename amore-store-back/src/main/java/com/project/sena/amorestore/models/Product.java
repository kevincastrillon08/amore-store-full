package com.project.sena.amorestore.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Category category;

    private String referenceNumber;

    private String name;

    private double price;

    private long stock;

    private String description;

    private boolean status;

    @Lob
    private byte[] image;

    private String imageFormat;


}

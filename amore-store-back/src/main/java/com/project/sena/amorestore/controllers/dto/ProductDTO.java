package com.project.sena.amorestore.controllers.dto;

import com.project.sena.amorestore.models.Category;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Data
public class ProductDTO {
    private long id;
    private Category category;
    private String referenceNumber;
    private String name;
    private double price;
    private long stock;
    private String description;
    private boolean status;
    private byte[] image;
    private String imageString;
    private String imageFormat;
}

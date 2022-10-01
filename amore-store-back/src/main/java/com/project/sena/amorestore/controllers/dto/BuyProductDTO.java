package com.project.sena.amorestore.controllers.dto;

import com.project.sena.amorestore.models.Category;
import lombok.Data;

@Data
public class BuyProductDTO {
    private ProductDTO product;
    private int amount;
    private double subtotal;
}

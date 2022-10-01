package com.project.sena.amorestore.controllers;

import com.project.sena.amorestore.controllers.dto.ProductDTO;
import com.project.sena.amorestore.models.Product;
import com.project.sena.amorestore.repositories.ProductRespository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

public class ProductController {

    @Autowired
    ProductRespository productRespository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/product/all")
    public List<ProductDTO> getAll() {
        List<Product> products = productRespository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .map(productDTO -> {
                    productDTO.setImageString(Base64.getEncoder().encodeToString(productDTO.getImage()));
                    return productDTO;
                }).collect(Collectors.toList());
                return productDTOS;
    }

    //crear
    @PostMapping("/product")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        Product savedProduct = productRespository.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    //actualizar

    @PutMapping("/product/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable long id) {
        Optional<Product> productOptional = productRespository.findById(id);

        if(!productOptional.isPresent())
            return ResponseEntity.notFound().build();

        Product savedProduct = productRespository.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    //eliminar

    @DeleteMapping("/products/{id}")
    public  void deleteProduct(@PathVariable long id) {
        productRespository.deleteById(id);
    }


    //buscarporid
    @GetMapping("/product/{id}")
    public ResponseEntity<Object> findProductById( @PathVariable long id) {
        Optional<Product> productOptional = productRespository.findById(id);

        if(!productOptional.isPresent())
            return ResponseEntity.notFound().build();

        return  ResponseEntity.ok(productOptional.get());
    }

}

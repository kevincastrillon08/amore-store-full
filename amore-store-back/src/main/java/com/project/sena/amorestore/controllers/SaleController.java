package com.project.sena.amorestore.controllers;

import com.project.sena.amorestore.controllers.dto.BuyProductDTO;
import com.project.sena.amorestore.controllers.dto.ErrorResponse;
import com.project.sena.amorestore.controllers.dto.ProductDTO;
import com.project.sena.amorestore.controllers.exception.ControledException;
import com.project.sena.amorestore.models.Product;
import com.project.sena.amorestore.models.Sale;
import com.project.sena.amorestore.models.SaleDetail;
import com.project.sena.amorestore.models.User;
import com.project.sena.amorestore.repositories.ProductRespository;
import com.project.sena.amorestore.repositories.SaleDetailRepository;
import com.project.sena.amorestore.repositories.SalesRepository;
import com.project.sena.amorestore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("sale")
public class SaleController {
    @Autowired
    SalesRepository salesRepository;
    @Autowired
    ProductRespository productRespository;
    @Autowired
    SaleDetailRepository saleDetailRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public List<Sale> getAll() {return salesRepository.findAll(); }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSale(@RequestBody Sale sale, @PathVariable long id) {
        Optional<Sale> saleOptional = salesRepository.findById(id);

        if (!saleOptional.isPresent())
            return ResponseEntity.notFound().build();

        Sale savedSale = salesRepository.save(sale);
        return ResponseEntity.ok(savedSale);
    }

    @PostMapping("/buy-products")
    public ResponseEntity<Object> buyProducts(@RequestBody List<BuyProductDTO> buyProductDTOList) throws ControledException {
        List<ErrorResponse> errorResponseList = new ArrayList<>();
        buyProductDTOList.stream().forEach(buyProductDTORequested -> {
            ProductDTO productRequested = buyProductDTORequested.getProduct();
            Optional<Product> optionalProduct = productRespository.findById(productRequested.getId());
            try {
                if(optionalProduct.isEmpty()) {
                    throw new ControledException("El producto " + productRequested.getName() + " no se encuentra disponible.");
                } else {
                    Product productGettedDB = optionalProduct.get();
                    if(productGettedDB.getStock() < buyProductDTORequested.getAmount()) {
                        throw new ControledException("El producto " + productRequested.getName() + " solo tiene disponible " + productGettedDB.getStock() + " en el inventario.");
                    }
                }
            } catch (ControledException e) {
                errorResponseList.add(ErrorResponse.builder()
                        .message("Error al comprar")
                        .detail(e.getMessage())
                        .build());
                e.printStackTrace();
            }
        });

        if(!errorResponseList.isEmpty()) {
            return ResponseEntity.ok(errorResponseList);
        }

        Optional<User> userOptional = userRepository.findById(1L);
        Sale sale = Sale.builder()
            .iva(0.19)
            .status(true)
            .voucherNumber(generateVoucherNumber())
            .dateTime(new Date())
            .user(userOptional.get())
        .build();

        Sale savedSale = salesRepository.save(sale);

        buyProductDTOList.stream().forEach(buyProductDTO -> {
            var productRequested = buyProductDTO.getProduct();
            Product productGetter = productRespository.findById(productRequested.getId()).get();

            SaleDetail saleDetail = SaleDetail.builder()
                    .sale(savedSale)
                    .product(productGetter)
                    .amount(buyProductDTO.getAmount())
                    .price(buyProductDTO.getAmount() * productRequested.getPrice())
                    .discount(0)
                    .build();

            saleDetailRepository.save(saleDetail);

            productGetter.setStock(productGetter.getStock() - buyProductDTO.getAmount());
            productRespository.save(productGetter);
        });

        return ResponseEntity.ok(savedSale);
    }

    private String generateVoucherNumber() {
        Random rn = new Random();
        Long number = rn.nextLong((9999999999L - 1000000000L) +1L) + 1000000000L;
        return number.toString();
    }
}

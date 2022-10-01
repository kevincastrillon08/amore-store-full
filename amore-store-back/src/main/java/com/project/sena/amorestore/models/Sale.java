package com.project.sena.amorestore.models;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    private String voucherNumber;

    private Date dateTime;

    private double iva;

    private double total;

    private boolean status;



}

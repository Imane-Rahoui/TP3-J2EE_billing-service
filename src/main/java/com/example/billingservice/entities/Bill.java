package com.example.billingservice.entities;

import com.example.billingservice.model.Customer;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date billingDate;

    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;

    private Long customerid;

    @Transient
    private Customer customer;


}

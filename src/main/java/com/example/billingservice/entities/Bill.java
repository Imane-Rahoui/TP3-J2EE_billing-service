package com.example.billingservice.entities;

import com.example.billingservice.enums.OrderStatus;
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

    private OrderStatus status;

    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;

    private Long customerid;

    @Transient
    private Customer customer;

    public double getTotal(){
        double somme=0;
        for(ProductItem pi:productItems){
            somme+=pi.getAmount();
        }
        return somme;
    }

}

package com.example.billingservice.entities;

import com.example.billingservice.model.Customer;
import com.example.billingservice.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double price;
    private long productID;
    private double discount;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // pr eviter les boucles
    @ManyToOne
    private Bill bill;
    @Transient
    private Product product;
    @Transient
    private String productName;
    public double getAmount(){
        return price*quantity*(1-discount);
    } //champ calculé

}

package com.example.billingservice.web;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.feign.CustomerRestClient;
import com.example.billingservice.feign.ProductItemRestClient;
import com.example.billingservice.model.Customer;
import com.example.billingservice.model.Product;
import com.example.billingservice.repository.BillRepository;
import com.example.billingservice.repository.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {

    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private ProductItemRestClient productItemRestClient;
    private CustomerRestClient customerRestClient;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, ProductItemRestClient productItemRestClient, CustomerRestClient customerRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.productItemRestClient = productItemRestClient;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id) {
        Bill bill = billRepository.findById(id).get();

        Customer customer = customerRestClient.getCustomerById(bill.getCustomerid());
        bill.setCustomer(customer);
        billRepository.save(bill);

        bill.getProductItems().forEach(
                productItem -> {
                    Product product=productItemRestClient.getProductById(productItem.getProductID());
                    productItem.setProduct(product);
                    productItem.setProductName(product.getName());
                }
        );
        return bill;
    }

}

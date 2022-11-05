package com.example.billingservice.feign;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.QnameValueType;
import com.example.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.QueryParam;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductItemRestClient {

    @GetMapping(path = "/products")
    PagedModel<Product> pageProducts(@RequestParam(value = "page") int page,
                                     @RequestParam(value = "size") int size); // le nom qui vs convient

    @GetMapping(path = "/products/{id}")
    Product getProductById(@PathVariable Long id);
}

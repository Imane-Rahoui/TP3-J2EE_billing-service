package com.example.billingservice.repository;

import com.example.billingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill,Long> {

    // http://localhost:8888/BILLING-SERVICE/bills/search/byCustomerId?customerId=1 //working now
    @RestResource(path = "/byCustomerId")
    List<Bill> findByCustomerid(@Param("customerId") Long customerId);
}

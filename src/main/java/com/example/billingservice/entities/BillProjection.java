package com.example.billingservice.entities;

import com.example.billingservice.enums.OrderStatus;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name="fullOrder",types = Bill.class)
public interface BillProjection {
    Long getId();
    Date getBillingDate();
    Long getCustomerid();
    OrderStatus getStatus();
}
// http://localhost:8888/BILLING-SERVICE/bills/search/byCustomerId?customerId=1&projection=fullOrder
// is working
// the order is important

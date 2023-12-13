package com.example.order.service.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private String orderId;
    private String product;
    private int quantity;
    private OrderStatus status;

}


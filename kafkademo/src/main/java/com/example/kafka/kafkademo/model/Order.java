package com.example.kafka.kafkademo.model;

import lombok.Data;

@Data
public class Order {
    private String orderId;
    private String product;
    private int quantity;
    private OrderStatus status;

}


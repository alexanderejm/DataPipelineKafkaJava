package com.example.kafka.kafkademo.kafka;

import lombok.Data;

@Data
public class Order {
    private String orderId;
    private String product;
    private int quantity;
    private OrderStatus status;

    // Constructors, getters, setters
}


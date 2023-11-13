package com.example.kafka.kafkademo.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProcessedEvent {
    private String orderId;
    private OrderStatus newStatus;

    // Constructors, getters, setters
}

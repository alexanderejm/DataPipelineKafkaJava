package com.example.kafka.kafkademo.event;

import com.example.kafka.kafkademo.model.OrderStatus;
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

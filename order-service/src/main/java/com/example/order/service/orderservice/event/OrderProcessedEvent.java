package com.example.order.service.orderservice.event;

import com.example.order.service.orderservice.model.OrderStatus;
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

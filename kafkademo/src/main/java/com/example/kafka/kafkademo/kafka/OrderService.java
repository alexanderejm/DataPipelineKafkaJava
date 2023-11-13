package com.example.kafka.kafkademo.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// Order Service
@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void placeOrder(Order order) {
        // Validate and process order logic

        // Publish OrderPlacedEvent to Kafka
        OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderId(), order.getProduct(), order.getQuantity());
        kafkaTemplate.send("order-events", "OrderPlaced", orderPlacedEvent);
    }

    public void processOrder(String orderId) {
        // Validate and process order logic

        // Publish OrderProcessedEvent to Kafka
        OrderProcessedEvent orderProcessedEvent = new OrderProcessedEvent(orderId, OrderStatus.PROCESSING);
        kafkaTemplate.send("order-events", "OrderProcessed", orderProcessedEvent);
    }

    public void cancelOrder(String orderId) {
        // Validate and process order cancellation logic

        // Publish OrderCancelledEvent to Kafka
        OrderCancelledEvent orderCancelledEvent = new OrderCancelledEvent(orderId);
        kafkaTemplate.send("order-events", "OrderCancelled", orderCancelledEvent);
    }
}
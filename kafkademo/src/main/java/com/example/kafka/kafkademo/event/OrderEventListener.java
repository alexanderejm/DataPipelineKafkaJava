package com.example.kafka.kafkademo.event;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

// Order Event Listener
@Slf4j
@Service
public class OrderEventListener {

    @KafkaListener(topics = "test", groupId = "my-group")
    public void listen(ConsumerRecord<String, Object> record) {
        String eventType = record.key();
        Object event = record.value();

        // Process events based on type
        switch (eventType) {
            case "OrderPlaced":
                handleOrderPlacedEvent((OrderPlacedEvent) event);
                break;
            case "OrderProcessed":
                handleOrderProcessedEvent((OrderProcessedEvent) event);
                break;
            case "OrderCancelled":
                handleOrderCancelledEvent((OrderCancelledEvent) event);
                break;
            default:
                // Handle unknown event type
        }
    }

    private void handleOrderPlacedEvent(OrderPlacedEvent event) {
        // Update local database or perform other actions
        // This may include updating the order status to "PROCESSING"
    }

    private void handleOrderProcessedEvent(OrderProcessedEvent event) {
        // Update local database or perform other actions
        // This may include updating the order status to the new status provided in the event
    }

    private void handleOrderCancelledEvent(OrderCancelledEvent event) {
        // Update local database or perform other actions
        // This may include marking the order as cancelled
    }
}
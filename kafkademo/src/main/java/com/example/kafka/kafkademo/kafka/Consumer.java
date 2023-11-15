package com.example.kafka.kafkademo.kafka;

import com.example.kafka.kafkademo.event.OrderPlacedEvent;
import com.example.kafka.kafkademo.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "order-events", groupId = "my-group")
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        System.out.println("TESTING CONSUMER");
        // Process the order placed event
        // Update local database or perform other actions
    }
}
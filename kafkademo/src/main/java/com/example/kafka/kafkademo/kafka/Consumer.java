package com.example.kafka.kafkademo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "test", groupId = "my-group")
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        // Process the order placed event
        // Update local database or perform other actions
    }
}
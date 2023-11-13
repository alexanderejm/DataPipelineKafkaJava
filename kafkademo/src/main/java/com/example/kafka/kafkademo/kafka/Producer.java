package com.example.kafka.kafkademo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void placeOrder(String order) {
        // Process order logic

        // Publish an event to Kafka topic
        kafkaTemplate.send("order-events", "OrderPlaced", order);
    }
}
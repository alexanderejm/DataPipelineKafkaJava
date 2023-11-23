package com.example.order.service.orderservice.kafka;

import com.example.order.service.orderservice.event.OrderPlacedEvent;
import com.example.order.service.orderservice.model.NewNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class Consumer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "order-events", groupId = "my-group")
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        System.out.println("TESTING CONSUMER");
        System.out.println(orderPlacedEvent.getOrderId());
        System.out.println(orderPlacedEvent.getProduct());
        System.out.println(orderPlacedEvent.getQuantity());
        NewNotification notification = new NewNotification();
        notification.setMessage(orderPlacedEvent.getProduct());
        //TODO: add userID to notification and define message
        HashMap<Integer, Object> notificationHashMap = new HashMap<Integer, Object>();
        notificationHashMap.put(0, orderPlacedEvent.getProduct());
        notificationHashMap.put(1, 1L);
        notification.setMessage(orderPlacedEvent.getProduct());
        notification.setUserId(1L);
        kafkaTemplate.send("notification2-events", "SendNotification", notificationHashMap);
        // Process the order placed event
        // Update local database or perform other actions
    }
}
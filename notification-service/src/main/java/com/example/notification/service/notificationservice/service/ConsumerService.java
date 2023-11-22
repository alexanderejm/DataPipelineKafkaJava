package com.example.notification.service.notificationservice.service;

import com.example.notification.service.notificationservice.model.NewNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ConsumerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "notification2-events", groupId = "notification-group")
    public void listen(HashMap<String, Object> notificationHashMap) {

        notificationService.sendNotificationByPreference(Long.valueOf((Integer) notificationHashMap.get("1")), (String) notificationHashMap.get("0"));
    }
}

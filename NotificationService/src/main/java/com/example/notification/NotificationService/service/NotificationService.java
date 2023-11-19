package com.example.notification.NotificationService.service;

import com.example.notification.NotificationService.model.Notification;
import com.example.notification.NotificationService.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Long notificationId) {
        return notificationRepository.findById(notificationId);
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    public void sendNotificationToKafka(Long userId, String message) {
        // Fetch user-specific notification configuration from your repository or service
        // You might need to implement a UserNotificationPreferencesService for this purpose
        // For simplicity, assuming a direct Kafka topic for each user (e.g., "user_notifications_<userId>")
        String kafkaTopic = "user_notifications_" + userId;

        // Send notification message to Kafka topic
        kafkaTemplate.send(kafkaTopic, message);
    }
}
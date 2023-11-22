package com.example.notification.service.notificationservice.service;

import com.example.notification.service.notificationservice.model.Notification;
import com.example.notification.service.notificationservice.model.UserNotificationPreferences;
import com.example.notification.service.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private UserNotificationPreferencesService userNotificationPreferencesService;

    @Autowired
    private UserPreferenceService userPreferenceService;


    public void sendNotificationToKafka(Long userId, String message) {
        // Fetch user-specific notification configuration
        List<UserNotificationPreferences> preferencesList = userNotificationPreferencesService.getUserNotificationPreferences(userId);

        // Check if the user has preferences and wants to receive notifications
        if (!preferencesList.isEmpty() && preferencesList.stream().anyMatch(UserNotificationPreferences::isReceiveNotifications)) {
            // Assuming a direct Kafka topic for each user (e.g., "user_notifications_<userId>")
            String kafkaTopic = "user_notifications_" + userId;

            // Send notification message to Kafka topic
            kafkaTemplate.send(kafkaTopic, message);
        }
    }

    public void sendNotificationByPreference(Long userId, String message) {
        // Fetch user-specific notification preferences
        List<UserNotificationPreferences> preferencesList = userNotificationPreferencesService.getActiveUserNotificationPreferences(userId);

        // Send notifications based on preferences
        for (UserNotificationPreferences preference : preferencesList) {
            sendNotificationToChannel(userId, preference.getChannel().getChannelName(), message);
        }
    }

    private void sendNotificationToChannel(Long userId, String channel, String message) {

        // Determine the channel type and send the notification accordingly
        switch (channel) {
            case "SMS":
                sendSmsNotification(userId, message);
                break;
            case "Email":
                sendEmailNotification(userId, message);
                break;
            // Add other channel types as needed
        }
    }

    private void sendSmsNotification(Long userId, String message) {
        // Logic to send SMS notification
        String phoneNumber = getUserPhoneNumber(userId);
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }

    private void sendEmailNotification(Long userId, String message) {
        // Logic to send email notification
        String emailAddress = getUserEmailAddress(userId);
        System.out.println("Sending Email to " + emailAddress + ": " + message);
    }

    private String getUserPhoneNumber(Long userId) {
        // Replace with actual logic to get user's phone number from the database
        return "123-456-7890";
    }

    private String getUserEmailAddress(Long userId) {
        // Replace with actual logic to get user's email address from the database
        return "user@example.com";
    }
}
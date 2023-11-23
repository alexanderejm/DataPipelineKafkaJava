package com.example.notification.service.notificationservice.service;

import com.example.notification.service.notificationservice.dto.UserNotificationPreferencesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    @KafkaListener(topics = "notification2-events", groupId = "notification-group")
    public void listen(HashMap<String, Object> notificationHashMap) {

        sendNotificationToUser(Long.valueOf((Integer) notificationHashMap.get("1")), (String) notificationHashMap.get("0"));
    }
    // Other methods in NotificationService

    public void sendNotificationToUser(Long userId, String message) {
        // Make an HTTP request to UserController endpoint to retrieve user notification preferences
        String userPreferencesUrl = "http://localhost:8083/api/users/" + userId + "/notification-preferences";

        UserNotificationPreferencesDto[] notificationPreferences = restTemplate.getForObject(
                userPreferencesUrl, UserNotificationPreferencesDto[].class);

        // Process the notification preferences and send the notification accordingly
        for (UserNotificationPreferencesDto preferences : notificationPreferences) {
            if (preferences.isReceiveNotifications()) {
                // Logic to send notification based on preferences (e.g., to Kafka)
                sendNotificationToChannel(message, preferences);
            }
        }
    }

    // Method to send notification (replace this with your actual logic)
    private void sendNotification(String message, UserNotificationPreferencesDto preferences) {
        // Implement your notification sending logic here
        // For example, you can send the notification to Kafka or any other messaging system
        System.out.println("Sending notification to user " + preferences.getUserId() +
                " via channel " + preferences.getChannelId() +
                " with message: " + message);
    }

    private void sendNotificationToChannel(String message, UserNotificationPreferencesDto userPreference) {

        // Determine the channel type and send the notification accordingly
        switch (userPreference.getChannelName()) {
            case "SMS":
                sendSmsNotification(userPreference.getUserId(), message);
                break;
            case "Email":
                sendEmailNotification(userPreference.getUserId(), message);
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
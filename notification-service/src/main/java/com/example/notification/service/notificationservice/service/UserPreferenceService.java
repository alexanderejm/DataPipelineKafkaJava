package com.example.notification.service.notificationservice.service;

import com.example.notification.service.notificationservice.model.UserNotificationPreferences;
import com.example.notification.service.notificationservice.repository.UserNotificationPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPreferenceService {

    @Autowired
    private UserNotificationPreferencesRepository userNotificationPreferencesRepository;

    public List<UserNotificationPreferences> getAllUserPreferences() {
        return userNotificationPreferencesRepository.findAll();
    }

    public List<UserNotificationPreferences> getUserPreferences(Long userId) {
        return userNotificationPreferencesRepository.findByIdUserId(userId);
    }

    public void updateUserPreferences(UserNotificationPreferences preferences) {
        // Assuming you want to perform some validation or business logic before updating
        userNotificationPreferencesRepository.save(preferences);
    }

    // Additional methods as needed

}

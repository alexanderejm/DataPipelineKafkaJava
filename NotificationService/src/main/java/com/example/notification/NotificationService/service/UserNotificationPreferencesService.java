package com.example.notification.NotificationService.service;

import com.example.notification.NotificationService.model.UserNotificationPreferences;
import com.example.notification.NotificationService.repository.UserNotificationPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserNotificationPreferencesService {

    @Autowired
    private UserNotificationPreferencesRepository userNotificationPreferencesRepository;

    public Optional<UserNotificationPreferences> getUserNotificationPreferences(Long userId, Long channelId) {
        return userNotificationPreferencesRepository.findById(userId, channelId);
    }
}

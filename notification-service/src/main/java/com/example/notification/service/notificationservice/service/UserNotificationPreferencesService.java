package com.example.notification.service.notificationservice.service;

import com.example.notification.service.notificationservice.model.UserNotificationPreferences;
import com.example.notification.service.notificationservice.repository.UserNotificationPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserNotificationPreferencesService {

    @Autowired
    private UserNotificationPreferencesRepository userNotificationPreferencesRepository;

    public List<UserNotificationPreferences> getUserNotificationPreferences(Long userId) {
        return userNotificationPreferencesRepository.findByIdUserId(userId);
    }

    public List<UserNotificationPreferences> getActiveUserNotificationPreferences(Long userId) {
        return userNotificationPreferencesRepository.findActivePreferencesByUserId(userId);
    }
}
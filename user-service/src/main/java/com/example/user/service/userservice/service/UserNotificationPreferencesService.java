package com.example.user.service.userservice.service;

import com.example.user.service.userservice.model.UserNotificationPreferences;
import com.example.user.service.userservice.repository.UserNotificationPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserNotificationPreferencesService {

    private final UserNotificationPreferencesRepository userNotificationPreferencesRepository;

    @Autowired
    public UserNotificationPreferencesService(UserNotificationPreferencesRepository userNotificationPreferencesRepository) {
        this.userNotificationPreferencesRepository = userNotificationPreferencesRepository;
    }

    public List<UserNotificationPreferences> getUserNotificationPreferences(Long userId) {
        return userNotificationPreferencesRepository.findByIdUserId(userId);
    }

    public List<UserNotificationPreferences> getActiveUserNotificationPreferences(Long userId) {
        return userNotificationPreferencesRepository.findActivePreferencesByUserId(userId);
    }
}
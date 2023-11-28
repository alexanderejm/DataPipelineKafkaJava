package com.example.user.service.userservice.service;

import com.example.user.service.userservice.model.NotificationChannel;
import com.example.user.service.userservice.model.User;
import com.example.user.service.userservice.model.UserNotificationPreferences;
import com.example.user.service.userservice.model.UserNotificationPreferencesId;
import com.example.user.service.userservice.repository.UserNotificationPreferencesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserNotificationPreferencesServiceTest {

    @Mock
    private UserNotificationPreferencesRepository userNotificationPreferencesRepository;

    @InjectMocks
    private UserNotificationPreferencesService userNotificationPreferencesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserNotificationPreferences_ShouldReturnUserNotificationPreferences() {
        // Arrange userId, 1L, true
        Long userId = 1L;
        List<UserNotificationPreferences> preferences = getUserNotificationPreferencesForTest();
        when(userNotificationPreferencesRepository.findByIdUserId(userId)).thenReturn(preferences);

        // Act
        List<UserNotificationPreferences> result = userNotificationPreferencesService.getUserNotificationPreferences(userId);

        // Assert
        assertEquals(preferences, result);
    }

    @Test
    void getActiveUserNotificationPreferences_ShouldReturnActiveUserNotificationPreferences() {
        // Arrange
        Long userId = 1L;
        List<UserNotificationPreferences> preferences = getUserNotificationPreferencesForTest();
        when(userNotificationPreferencesRepository.findActivePreferencesByUserId(userId)).thenReturn(preferences);

        // Act
        List<UserNotificationPreferences> result = userNotificationPreferencesService.getActiveUserNotificationPreferences(userId);

        // Assert
        assertEquals(preferences, result);
    }

    List<UserNotificationPreferences> getUserNotificationPreferencesForTest(){
        List<UserNotificationPreferences> users = new ArrayList<>();

        User user1 = new User();
        user1.setUsername("john_doe");
        user1.setEmail("john.doe@example.com");

        User user2 = new User();
        user2.setUsername("jane_smith");
        user2.setEmail("jane.smith@example.com");

        NotificationChannel emailChannel = new NotificationChannel();
        emailChannel.setChannelName("Email");

        NotificationChannel smsChannel = new NotificationChannel();
        smsChannel.setChannelName("SMS");

        // Assuming you have channelId and userId values from the created instances above
        UserNotificationPreferencesId id1 = new UserNotificationPreferencesId();
        id1.setUserId(user1.getUserId());
        id1.setChannelId(emailChannel.getChannelId());

        UserNotificationPreferences userNotificationPreferences1 = new UserNotificationPreferences();
        userNotificationPreferences1.setId(id1);
        userNotificationPreferences1.setUser(user1);
        userNotificationPreferences1.setChannel(emailChannel);
        userNotificationPreferences1.setReceiveNotifications(true);

        UserNotificationPreferencesId id2 = new UserNotificationPreferencesId();
        id2.setUserId(user2.getUserId());
        id2.setChannelId(smsChannel.getChannelId());

        UserNotificationPreferences userNotificationPreferences2 = new UserNotificationPreferences();
        userNotificationPreferences2.setId(id2);
        userNotificationPreferences2.setUser(user2);
        userNotificationPreferences2.setChannel(smsChannel);
        userNotificationPreferences2.setReceiveNotifications(false);

        users.add(userNotificationPreferences1);
        users.add(userNotificationPreferences2);
        return users;
    }

    List<User> getUsersForTest() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("john_doe");
        user1.setEmail("john.doe@example.com");

        User user2 = new User();
        user2.setUsername("jane_smith");
        user2.setEmail("jane.smith@example.com");

        users.add(user1);
        users.add(user2);
        return users;
    }
}
package com.example.notification.service.notificationservice.service;

import com.example.notification.service.notificationservice.dto.UserNotificationPreferencesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendNotificationToUser_ShouldSendNotification_WhenPreferencesExist() {
        // Arrange
        Long userId = 1L;
        String message = "Test Message";

        // Mock the restTemplate behavior
        UserNotificationPreferencesDto preference1 = new UserNotificationPreferencesDto(userId, 1L, "Email", true);
        UserNotificationPreferencesDto preference2 = new UserNotificationPreferencesDto(userId, 2L, "SMS", true);
        UserNotificationPreferencesDto[] preferences = {preference1, preference2};
        when(restTemplate.getForObject(any(), any())).thenReturn(preferences);

        // Mock the behavior of sendNotificationToChannel method
        doNothing().when(notificationService).sendNotificationToChannel(any(), any());

        // Act
        notificationService.sendNotificationToUser(userId, message);

        // Assert
        verify(notificationService, times(1)).sendNotificationToChannel(eq(message), eq(preference1));
        verify(notificationService, times(1)).sendNotificationToChannel(eq(message), eq(preference2));
    }

    @Test
    void sendNotificationToUser_ShouldNotSendNotification_WhenNoPreferences() {
        // Arrange
        Long userId = 1L;
        String message = "Test Message";

        // Mock the restTemplate behavior to return an empty array
        when(restTemplate.getForObject(any(), any())).thenReturn(new UserNotificationPreferencesDto[0]);

        // Act
        notificationService.sendNotificationToUser(userId, message);

        // Assert
        verify(notificationService, never()).sendNotificationToChannel(any(), any());
    }
}
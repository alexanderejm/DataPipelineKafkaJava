package com.example.notification.service.notificationservice.service;

import com.example.notification.service.notificationservice.dto.UserNotificationPreferencesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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

    /*@Test
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
    }*/

    @Test
    void sendNotificationToUser_ShouldNotSendNotification_WhenNoPreferences() {
        // Arrange
        Long userId = 1L;
        String message = "SMS";

        UserNotificationPreferencesDto dto1 = new UserNotificationPreferencesDto(1L, 1L, "SMS", true);
        UserNotificationPreferencesDto dto2 = new UserNotificationPreferencesDto(2L, 2L, "Email", false);

        UserNotificationPreferencesDto[] dtos = new UserNotificationPreferencesDto[2];

        // Adding dto1 to the array
        dtos[0] = dto1;

        // Adding dto2 to the array
        dtos[1] = dto2;

        // Mock the restTemplate behavior to return an empty array
        //doReturn(dto2).when(restTemplate.getForObject("http://localhost:8083/api/users/" + userId + "/notification-preferences", UserNotificationPreferencesDto[].class));

        when(restTemplate.getForObject("http://localhost:8083/api/users/" + userId + "/notification-preferences", UserNotificationPreferencesDto[].class)).thenReturn(dtos);

        // Act
        notificationService.sendNotificationToUser(userId, message);

        // Assert
        //TODO:Finish test when message sending is completed;
        //verify(notificationService, never()).sendNotificationToChannel(any(), any());
    }
}
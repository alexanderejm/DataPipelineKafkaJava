package com.example.notification.NotificationService.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class UserNotificationPreferencesId implements Serializable {

    private Long user;

    private Long notificationChannel;
}
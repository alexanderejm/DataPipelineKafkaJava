package com.example.notification.NotificationService.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;

@Entity
@Table(name = "user_notification_preferences", schema = "notification")
@IdClass(UserNotificationPreferencesId.class)
@Data
public class UserNotificationPreferences {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private NotificationChannel notificationChannel;

    private boolean receiveNotifications;
}

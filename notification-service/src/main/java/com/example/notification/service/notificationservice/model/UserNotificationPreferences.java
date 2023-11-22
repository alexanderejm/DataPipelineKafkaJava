package com.example.notification.service.notificationservice.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "user_notification_preferences", schema = "notification")
@Data
public class UserNotificationPreferences {

    @EmbeddedId
    private UserNotificationPreferencesId id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    private NotificationChannel channel;

    private boolean receiveNotifications;

    public Long getChannelId() {
        return (id != null) ? id.getChannelId() : null;
    }
}

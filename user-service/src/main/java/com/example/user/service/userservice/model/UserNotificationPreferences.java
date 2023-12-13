package com.example.user.service.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_notification_preferences", schema = "users")
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

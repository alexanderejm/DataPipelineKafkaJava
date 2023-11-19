package com.example.notification.NotificationService.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "notification_channels", schema = "notification")
@Data
public class NotificationChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long channelId;

    private String channelName;
    // Other fields
}
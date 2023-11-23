package com.example.user.service.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

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
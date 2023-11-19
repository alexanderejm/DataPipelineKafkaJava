package com.example.notification.NotificationService.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "notifications", schema = "notification")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;
    // Other fields
}

package com.example.notification.service.notificationservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users", schema = "notification")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String email;
}

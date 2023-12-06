package com.example.user.service.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users", schema = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String email;

    private String phoneNumber;
}

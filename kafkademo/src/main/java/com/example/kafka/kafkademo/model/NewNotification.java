package com.example.kafka.kafkademo.model;

import lombok.Data;

@Data
public class NewNotification {

    private String message;
    private Long userId;
}

package com.example.kafka.kafkademo.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED, PROCESSING, COMPLETED, CANCELLED
}

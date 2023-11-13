package com.example.kafka.kafkademo.kafka;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED, PROCESSING, COMPLETED, CANCELLED
}

package com.example.order.service.orderservice.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED, PROCESSING, COMPLETED, CANCELLED
}

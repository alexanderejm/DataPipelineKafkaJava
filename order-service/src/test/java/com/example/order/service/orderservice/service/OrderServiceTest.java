package com.example.order.service.orderservice.service;

import com.example.order.service.orderservice.event.OrderCancelledEvent;
import com.example.order.service.orderservice.event.OrderPlacedEvent;
import com.example.order.service.orderservice.event.OrderProcessedEvent;
import com.example.order.service.orderservice.model.Order;
import com.example.order.service.orderservice.model.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static com.example.order.service.orderservice.model.OrderStatus.CREATED;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class OrderServiceTest {

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void placeOrder_ShouldPublishOrderPlacedEvent() {
        // Arrange

        Order order = new Order("123", "Product1", 5,CREATED);

        // Act
        orderService.placeOrder(order);

        // Assert
        verify(kafkaTemplate, times(1)).send(eq("order-events"), eq("OrderPlaced"), any(OrderPlacedEvent.class));
    }

    @Test
    void processOrder_ShouldPublishOrderProcessedEvent() {
        // Arrange
        String orderId = "123";

        // Act
        orderService.processOrder(orderId);

        // Assert
        verify(kafkaTemplate, times(1)).send(eq("order-events"), eq("OrderProcessed"), any(OrderProcessedEvent.class));
    }

    @Test
    void cancelOrder_ShouldPublishOrderCancelledEvent() {
        // Arrange
        String orderId = "123";

        // Act
        orderService.cancelOrder(orderId);

        // Assert
        verify(kafkaTemplate, times(1)).send(eq("order-events"), eq("OrderCancelled"), any(OrderCancelledEvent.class));
    }
}
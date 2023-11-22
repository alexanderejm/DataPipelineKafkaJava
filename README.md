# Microservices Architecture for Notification System

## Overview

This system is designed as a microservices architecture to handle order creation, user registration, and notifications based on user preferences.

## Microservices

### 1. Order Service

#### Responsibilities
- Handles the creation and management of orders.
- Manages order-related business logic.

#### Interactions
- Produces "OrderCreated" events to Kafka when a new order is created.
- REST API for order creation and management.

### 2. User Service

#### Responsibilities
- Manages user registration and user-related business logic.
- Keeps track of user notification preferences.

#### Interactions
- REST API for user registration and management.
- Stores user preferences in its database.
- May consume "OrderCreated" events from Kafka to update user-related information.

### 3. Notification Service

#### Responsibilities
- Listens for "OrderCreated" events from Kafka.
- Retrieves user preferences from the User Service based on the event.
- Sends notifications to users based on their preferences.

#### Interactions
- Consumes "OrderCreated" events from Kafka.
- Communicates with the User Service via REST API to retrieve user preferences.
- Sends notifications to users (through appropriate channels like email, SMS) based on preferences.

## Kafka Topics

### 1. OrderCreated Topic

- Produced by the Order Service when a new order is created.
- Consumed by the Notification Service to trigger notifications.

## REST APIs

### 1. Order Service API

- Endpoint for creating and managing orders.

### 2. User Service API

- Endpoint for user registration and management.
- Endpoint to retrieve user notification preferences.

### 3. Notification Service API

- No public API (internal communication with other services).
- May expose metrics or monitoring endpoints.

## Workflow

1. User registers using the User Service.
2. User sets notification preferences using the User Service.
3. Order is created using the Order Service, producing an "OrderCreated" event.
4. Notification Service consumes the "OrderCreated" event.
5. Notification Service retrieves user preferences from the User Service.
6. Notification Service sends notifications to users based on their preferences.

## Considerations

- Each service has its own database schema.
- Microservices communicate asynchronously via Kafka to avoid tight coupling.
- Ensure proper error handling, retries, and security measures.
- Monitor and log interactions between services for troubleshooting and performance optimization(not in scope yet).
- Potential extra authenticator service to allow login to the current available endpoints(not in scope yet).

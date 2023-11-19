package com.example.notification.NotificationService.repository;

import com.example.notification.NotificationService.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Additional query methods can be defined here if needed
}

package com.example.notification.service.notificationservice.repository;

import com.example.notification.service.notificationservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


    // Additional query methods can be defined here if needed
}

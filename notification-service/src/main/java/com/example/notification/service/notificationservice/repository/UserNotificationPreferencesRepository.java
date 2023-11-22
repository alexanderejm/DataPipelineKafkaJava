package com.example.notification.service.notificationservice.repository;

import com.example.notification.service.notificationservice.model.UserNotificationPreferences;
import com.example.notification.service.notificationservice.model.UserNotificationPreferencesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserNotificationPreferencesRepository extends JpaRepository<UserNotificationPreferences, UserNotificationPreferencesId> {

    List<UserNotificationPreferences> findByIdUserId(Long userId);

    @Query("SELECT unp FROM UserNotificationPreferences unp WHERE unp.id.userId = :userId AND unp.receiveNotifications = true")
    List<UserNotificationPreferences> findActivePreferencesByUserId(@Param("userId") Long userId);

    // Additional custom query methods can be added as needed
}

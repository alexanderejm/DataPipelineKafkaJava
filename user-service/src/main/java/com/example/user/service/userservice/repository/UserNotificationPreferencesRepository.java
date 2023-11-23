package com.example.user.service.userservice.repository;

import com.example.user.service.userservice.model.UserNotificationPreferences;
import com.example.user.service.userservice.model.UserNotificationPreferencesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserNotificationPreferencesRepository extends JpaRepository<UserNotificationPreferences, UserNotificationPreferencesId> {

    List<UserNotificationPreferences> findByIdUserId(Long userId);

    @Query("SELECT unp FROM UserNotificationPreferences unp WHERE unp.id.userId = :userId AND unp.receiveNotifications = true")
    List<UserNotificationPreferences> findActivePreferencesByUserId(@Param("userId") Long userId);

    // Additional custom query methods can be added as needed
}

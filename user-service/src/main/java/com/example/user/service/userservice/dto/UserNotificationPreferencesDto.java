package com.example.user.service.userservice.dto;

import lombok.Data;

@Data
public class UserNotificationPreferencesDto {

    private Long userId;
    private Long channelId;
    private String channelName;
    private boolean receiveNotifications;

    // Constructors, getters, and setters

    public UserNotificationPreferencesDto() {
    }

    public UserNotificationPreferencesDto(Long userId, Long channelId, String channelName, boolean receiveNotifications) {
        this.userId = userId;
        this.channelId = channelId;
        this.channelName = channelName;
        this.receiveNotifications = receiveNotifications;
    }

}

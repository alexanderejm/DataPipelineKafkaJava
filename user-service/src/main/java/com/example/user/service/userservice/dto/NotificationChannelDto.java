package com.example.user.service.userservice.dto;

import lombok.Data;

@Data
public class NotificationChannelDto {

    private Long channelId;
    private String channelName;

    // Constructors, getters, and setters

    public NotificationChannelDto() {
    }

    public NotificationChannelDto(Long channelId, String channelName) {
        this.channelId = channelId;
        this.channelName = channelName;
    }

    // Getters and setters
}

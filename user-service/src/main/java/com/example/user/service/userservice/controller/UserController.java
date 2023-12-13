package com.example.user.service.userservice.controller;

import com.example.user.service.userservice.dto.UserDto;
import com.example.user.service.userservice.dto.UserNotificationPreferencesDto;
import com.example.user.service.userservice.model.User;
import com.example.user.service.userservice.model.UserNotificationPreferences;
import com.example.user.service.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
        User newUser = userService.addUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        // Retrieve user details by userId
        Optional<User> optionalUser = userService.getUserById(userId);
        if (optionalUser.isPresent()) {
            // Map User to UserDto if needed
            UserDto userDto = mapUserToDto(optionalUser.get());
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{userId}/notification-preferences")
    public ResponseEntity<?> getUserNotificationPreferences(@PathVariable Long userId) {
        // Retrieve user notification preferences by userId
        List<UserNotificationPreferences> notificationPreferences = userService.getUserNotificationPreferences(userId);

        if (!notificationPreferences.isEmpty()) {
            // Map UserNotificationPreferences to UserNotificationPreferencesDto
            List<UserNotificationPreferencesDto> notificationPreferencesDtoList = mapToDtoList(notificationPreferences);
            return new ResponseEntity<>(notificationPreferencesDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User has no notification preferences", HttpStatus.NOT_FOUND);
        }
    }

    // Helper method to map UserNotificationPreferences to UserNotificationPreferencesDto
    private List<UserNotificationPreferencesDto> mapToDtoList(List<UserNotificationPreferences> preferencesList) {
        return preferencesList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Helper method to map UserNotificationPreferences to UserNotificationPreferencesDto
    private UserNotificationPreferencesDto mapToDto(UserNotificationPreferences preferences) {
        return new UserNotificationPreferencesDto(
                preferences.getId().getUserId(),
                preferences.getId().getChannelId(),
                preferences.getChannel().getChannelName(),
                preferences.isReceiveNotifications()
        );
    }

    // Map User to UserDto if needed
    private UserDto mapUserToDto(User user) {
        // Your mapping logic here
        return new UserDto(user.getUserId(), user.getUsername(), user.getEmail(), user.getPhoneNumber());
    }

    // Add more endpoints as needed for user management

}
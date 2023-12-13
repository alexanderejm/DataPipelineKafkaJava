package com.example.user.service.userservice.service;

import com.example.user.service.userservice.dto.UserDto;
import com.example.user.service.userservice.dto.UserNotificationPreferencesDto;
import com.example.user.service.userservice.model.User;
import com.example.user.service.userservice.model.UserNotificationPreferences;
import com.example.user.service.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserNotificationPreferencesService userNotificationPreferencesService;

    @Autowired
    public UserService(UserRepository userRepository, UserNotificationPreferencesService userNotificationPreferencesService) {
        this.userRepository = userRepository;
        this.userNotificationPreferencesService = userNotificationPreferencesService;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<UserNotificationPreferences> getUserNotificationPreferences(Long userId) {
        // Fetch user-specific notification preferences
        return userNotificationPreferencesService.getActiveUserNotificationPreferences(userId);

    }

    public User addUser(UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setPhoneNumber(userDto.getPhoneNumber());
        return userRepository.save(newUser);
    }
}
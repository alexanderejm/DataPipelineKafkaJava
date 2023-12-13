package com.example.user.service.userservice.service;

import com.example.user.service.userservice.dto.UserDto;
import com.example.user.service.userservice.model.NotificationChannel;
import com.example.user.service.userservice.model.User;
import com.example.user.service.userservice.model.UserNotificationPreferences;
import com.example.user.service.userservice.model.UserNotificationPreferencesId;
import com.example.user.service.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserNotificationPreferencesService userNotificationPreferencesService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_ShouldReturnAllUsers() {
        // Arrange
        List<User> users = getUsersForTest();
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertEquals(users, result);
    }

    @Test
    void getUserById_ShouldReturnUserById() {
        // Arrange
        Long userId = 1L;
        User user1 = new User();
        user1.setUsername("john_doe");
        user1.setEmail("john.doe@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user1));

        // Act
        Optional<User> result = userService.getUserById(userId);

        // Assert
        assertEquals(Optional.of(user1), result);
    }

    @Test
    void saveUser_ShouldSaveUser() {
        // Arrange
        User user1 = new User();
        user1.setUsername("john_doe");
        user1.setEmail("john.doe@example.com");
        when(userRepository.save(any())).thenReturn(user1);

        // Act
        User result = userService.saveUser(user1);

        // Assert
        assertEquals(user1, result);
    }

    @Test
    void deleteUser_ShouldDeleteUser() {
        // Arrange
        Long userId = 1L;

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void getUserNotificationPreferences_ShouldReturnUserNotificationPreferences() {
        // Arrange
        Long userId = 1L;
        List<UserNotificationPreferences> preferences = getUserNotificationPreferencesForTest();
        when(userNotificationPreferencesService.getActiveUserNotificationPreferences(userId)).thenReturn(preferences);

        // Act
        List<UserNotificationPreferences> result = userService.getUserNotificationPreferences(userId);

        // Assert
        assertEquals(preferences, result);
    }

    @Test
    void addUser() {
        // Mocking the behavior of the userRepository.save method
        when(userRepository.save(any())).thenReturn(createMockUser());

        // Creating a UserDto for testing
        UserDto userDto = createMockUserDto();

        // Calling the addUser method
        userService.addUser(userDto);

        // Verifying that the save method of userRepository was called with the expected argument
        verify(userRepository).save(any());
    }

    private UserDto createMockUserDto() {
        return new UserDto(1L,"john_doe", "john.doe@example.com","111111111");
    }

    private User createMockUser() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        return user;
    }

    List<UserNotificationPreferences> getUserNotificationPreferencesForTest(){
        List<UserNotificationPreferences> users = new ArrayList<>();

        User user1 = new User();
        user1.setUsername("john_doe");
        user1.setEmail("john.doe@example.com");

        User user2 = new User();
        user2.setUsername("jane_smith");
        user2.setEmail("jane.smith@example.com");

        NotificationChannel emailChannel = new NotificationChannel();
        emailChannel.setChannelName("Email");

        NotificationChannel smsChannel = new NotificationChannel();
        smsChannel.setChannelName("SMS");

        // Assuming you have channelId and userId values from the created instances above
        UserNotificationPreferencesId id1 = new UserNotificationPreferencesId();
        id1.setUserId(user1.getUserId());
        id1.setChannelId(emailChannel.getChannelId());

        UserNotificationPreferences userNotificationPreferences1 = new UserNotificationPreferences();
        userNotificationPreferences1.setId(id1);
        userNotificationPreferences1.setUser(user1);
        userNotificationPreferences1.setChannel(emailChannel);
        userNotificationPreferences1.setReceiveNotifications(true);

        UserNotificationPreferencesId id2 = new UserNotificationPreferencesId();
        id2.setUserId(user2.getUserId());
        id2.setChannelId(smsChannel.getChannelId());

        UserNotificationPreferences userNotificationPreferences2 = new UserNotificationPreferences();
        userNotificationPreferences2.setId(id2);
        userNotificationPreferences2.setUser(user2);
        userNotificationPreferences2.setChannel(smsChannel);
        userNotificationPreferences2.setReceiveNotifications(false);

        users.add(userNotificationPreferences1);
        users.add(userNotificationPreferences2);
        return users;
    }

    List<User> getUsersForTest() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("john_doe");
        user1.setEmail("john.doe@example.com");

        User user2 = new User();
        user2.setUsername("jane_smith");
        user2.setEmail("jane.smith@example.com");

        users.add(user1);
        users.add(user2);
        return users;
    }
}
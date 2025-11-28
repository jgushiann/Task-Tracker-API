package com.nini.TaskTrackerAPI.Service;

import com.nini.TaskTrackerAPI.dto.UserRequestDTO;
import com.nini.TaskTrackerAPI.dto.UserResponseDTO;
import com.nini.TaskTrackerAPI.mapper.UserMapper;
import com.nini.TaskTrackerAPI.model.Role;
import com.nini.TaskTrackerAPI.model.User;
import com.nini.TaskTrackerAPI.repository.UserRepository;
import com.nini.TaskTrackerAPI.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUser() {
        UserRequestDTO userRequestDTO = new UserRequestDTO(
                null,
                "Nini's Firstname",
                "Jgushia",
                "njgus@example.com",
                "jgushiann",
                "nini'sPassword"
        );
        User mappedUser = new User();
        mappedUser.setFirstName("Nini's Firstname");
        mappedUser.setLastName("Jgushia");
        mappedUser.setEmail("njgus@example.com");
        mappedUser.setUsername("jgushiann");

        User savedUser = new User();
        savedUser.setFirstName("Nini's Firstname");
        savedUser.setLastName("Jgushia");
        savedUser.setEmail("njgus@example.com");
        savedUser.setUsername("jgushiann");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setFirstName("Nini's Firstname");
        userResponseDTO.setLastName("Jgushia");
        userResponseDTO.setEmail("njgus@example.com");
        userResponseDTO.setUsername("jgushiann");

        when(userMapper.toEntity(userRequestDTO))
                .thenReturn(mappedUser);
        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);
        when(userMapper.toDto(mappedUser))
                .thenReturn(userResponseDTO);

        UserResponseDTO result = userService.createUser(userRequestDTO);

        assertEquals("Nini's Firstname", result.getFirstName());
        assertEquals("njgus@example.com", result.getEmail());

        verify(userRepository, times(1)).save(any(User.class));
    }
}
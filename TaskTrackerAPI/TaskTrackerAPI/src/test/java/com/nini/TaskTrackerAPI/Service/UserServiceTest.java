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

import java.util.ArrayList;
import java.util.List;

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
    void shouldSearchUser(){
        String firstname = "user";

        User user1 = new User();
        user1.setUserId(1);
        user1.setUsername("user");

        User user2 = new User();
        user2.setUserId(2);
        user2.setUsername("user2");

        UserResponseDTO userResponseDTO1 = new UserResponseDTO();
        userResponseDTO1.setUsername("user");

        UserResponseDTO userResponseDTO2 = new UserResponseDTO();
        userResponseDTO2.setUsername("user2");

        when(userMapper.toDto(user1))
                .thenReturn(userResponseDTO1);
        when(userMapper.toDto(user2))
                .thenReturn(userResponseDTO2);
        when(userRepository.searchUsers(firstname, null, null, null, null))
                .thenReturn(List.of(user1));
        when(userRepository.findAll())
                .thenReturn(List.of(user1, user2));

        List<UserResponseDTO> result = userService.searchUser(firstname, null, null, null, null);

        assertEquals(1, result.size());
        assertEquals(userResponseDTO1, result.get(0));

        List<UserResponseDTO> result_all = userService.searchUser(null, null, null, null, null);

        assertEquals(2, result_all.size());
        assertEquals(userResponseDTO1, result_all.get(0));
        assertEquals(userResponseDTO2, result_all.get(1));

        verify(userRepository).searchUsers(firstname, null, null, null, null);
    }
    
    @Test
    void shouldSearchUserByUserId(){

    }

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

    @Test
    void shouldUpdateUser() {

    }


}
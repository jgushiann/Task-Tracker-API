package com.nini.TaskTrackerAPI.service;

import com.nini.TaskTrackerAPI.dto.TaskResponseDTO;
import com.nini.TaskTrackerAPI.dto.UserRequestDTO;
import com.nini.TaskTrackerAPI.dto.UserResponseDTO;
import com.nini.TaskTrackerAPI.exception.AlreadyExistsException;
import com.nini.TaskTrackerAPI.exception.NotFoundException;
import com.nini.TaskTrackerAPI.mapper.TaskMapper;
import com.nini.TaskTrackerAPI.mapper.UserMapper;
import com.nini.TaskTrackerAPI.model.User;
import com.nini.TaskTrackerAPI.repository.TaskRepository;
import com.nini.TaskTrackerAPI.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    private final UserMapper userMapper;

    private final TaskMapper taskMapper;

    public List<UserResponseDTO> searchUser(String firstname, String lastname,String username, String email, Long id) {
        if (firstname != null) return getUserByFirstNameContaining(firstname);
        if (lastname != null) return getUserByLastNameContaining(lastname);
        if (username != null) return getUserByUsernameContaining(username);
        if (email != null) return getUserByEmailContaining(email);
        if (id != null) return List.of(getUserById(id));

        return getAll();
    }

    public User searchUserByUserId(Long user_id){
        return userRepository.findById(user_id).orElse(null);
    }

    @Transactional
    public UserResponseDTO updateUser(Long user_id, UserRequestDTO updatedUserDTO) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        User updated = userMapper.updateEntity(updatedUserDTO, user);
        userRepository.save(user);
        return userMapper.toDto(updated);
    }

    @Transactional
    public void deleteUsers(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.deleteByUserId(user.getUserId());
    }

    public List<TaskResponseDTO> getTasksByUserId(Long user_id){
        User user = userRepository.findByUserId(user_id).orElseThrow(() -> new NotFoundException("User not found"));
        return taskRepository.findByAssignedUser(user)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userDTO){
        if(userRepository.findByUsername(userDTO.getUsername()).isPresent()){
            throw new AlreadyExistsException("User already exists");
        }
        User user = userMapper.toEntity(userDTO);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    private List<UserResponseDTO> getAll(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    private UserResponseDTO getUserById(Long id) {
        User user = userRepository.findByUserId(id)
                .orElseThrow(() -> new NotFoundException("User not found with id" + id));

        return userMapper.toDto(user);
    }

    private List<UserResponseDTO> getUserByFirstNameContaining(String firstName){
        return userRepository.findByFirstNameContaining(firstName)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    private List<UserResponseDTO> getUserByLastNameContaining(String lastName){
        return userRepository.findByLastNameContaining(lastName)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    private List<UserResponseDTO> getUserByEmailContaining(String email){
        return userRepository.findByEmailContaining(email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    private List<UserResponseDTO> getUserByUsernameContaining(String username){
        return userRepository.findByUsernameContaining(username)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found with username: " + username));
    }
}

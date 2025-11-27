package com.nini.TaskTrackerAPI.service;

import com.nini.TaskTrackerAPI.dto.TaskResponseDTO;
import com.nini.TaskTrackerAPI.dto.UserRequestDTO;
import com.nini.TaskTrackerAPI.dto.UserResponseDTO;
import com.nini.TaskTrackerAPI.exception.AlreadyExistsException;
import com.nini.TaskTrackerAPI.exception.NotFoundException;
import com.nini.TaskTrackerAPI.mapper.TaskMapper;
import com.nini.TaskTrackerAPI.mapper.UserMapper;
import com.nini.TaskTrackerAPI.model.Role;
import com.nini.TaskTrackerAPI.model.User;
import com.nini.TaskTrackerAPI.repository.TaskRepository;
import com.nini.TaskTrackerAPI.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        if(firstname == null && lastname == null && username == null && email == null && id == null) {
            return getAll();
        }
        return userRepository.searchUsers(firstname, lastname, username, email, id).stream().map(userMapper::toDto).toList();
    }

    public User searchUserByUserId(Long user_id){
        return userRepository.findById(user_id).orElseThrow(()->new NotFoundException("User not found"));
    }

    @Transactional
    public UserResponseDTO updateUser(Long user_id, UserRequestDTO updatedUserDTO) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        User updated = userMapper.updateEntity(updatedUserDTO, user);
        userRepository.save(updated);
        return userMapper.toDto(updated);
    }

    @Transactional
    public void deleteUsers(Long user_id) {
        if(!userRepository.existsById(user_id)) {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(user_id);
    }

    public List<TaskResponseDTO> getTasksByUserId(Long user_id){
        User user = userRepository.findById(user_id).orElseThrow(() -> new NotFoundException("User not found"));
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
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new AlreadyExistsException("Email already exists");
        }
        User user = userMapper.toEntity(userDTO);
        user.setRole(Role.USER);
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    private List<UserResponseDTO> getAll(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}

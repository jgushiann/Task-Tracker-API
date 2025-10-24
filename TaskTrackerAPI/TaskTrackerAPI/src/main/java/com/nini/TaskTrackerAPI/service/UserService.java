package com.nini.TaskTrackerAPI.service;

import com.nini.TaskTrackerAPI.model.Task;
import com.nini.TaskTrackerAPI.model.User;
import com.nini.TaskTrackerAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) throws Exception {
        return userRepository.findByUser_id(id)
                .orElseThrow(() -> new Exception("User not found with id" + id));
    }

    public List<User> getUserByFirstName(String firstName) throws Exception {
        return userRepository.findByFirst_name(firstName);
    }

    public List<User> getUserByLastName(String lastName) throws Exception {
        return userRepository.findByLast_name(lastName);
    }

    public User getUserByEmail(String email) throws Exception {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User not found with this email" + email));
    }

    public User getUserByUsername(String username) throws Exception {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found with username " + username));
    }

    public List<User> getUserByFirstNameContaining(String firstName){
        return userRepository.findByFirst_nameContaining(firstName);
    }

    public List<User> getUserByLastNameContaining(String lastName){
        return userRepository.findByLast_nameContaining(lastName);
    }

    public List<User> getUserByEmailContaining(String email){
        return userRepository.findByEmailContaining(email);
    }

    public List<User> getUserByUsernameContaining(String username){
        return userRepository.findByUsernameContaining(username);
    }

    public List<Task> getTasksByUserId(Long user_id) throws Exception {
        return userRepository.findAllByUser_id(user_id);
    }

    public void deleteUserById(Long id) throws Exception {
        if(userRepository.existsByUser_id(id)){
            userRepository.deleteByUser_id(id);
        }else{
            throw new Exception("User not found with id" + id);
        }
    }

    public void deleteUserByFirstName(String firstName) throws Exception {
        if(userRepository.existsByFirst_name(firstName)){
            userRepository.deleteByFirst_name(firstName);
        }else{
            throw new Exception("User not found with first name " + firstName);
        }
    }

    public void deleteUserByLastName(String lastName) throws Exception {
        if(userRepository.existsByLast_name(lastName)){
            userRepository.deleteByLast_name(lastName);
        }else{
            throw new Exception("User not found with last name " + lastName);
        }
    }

    public void deleteUserByEmail(String email) throws Exception {
        if(userRepository.existsByEmail(email)){
            userRepository.deleteByEmail(email);
        }else{
            throw new Exception("User not found with email " + email);
        }
    }

    public void deleteUserByUsername(String username) throws Exception {
        if(userRepository.existsByUsername(username)){
            userRepository.deleteByUsername(username);
        }else{
            throw new Exception("User not found with username " + username);
        }
    }
}

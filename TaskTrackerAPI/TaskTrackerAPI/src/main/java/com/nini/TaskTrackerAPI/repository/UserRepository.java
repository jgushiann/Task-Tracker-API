package com.nini.TaskTrackerAPI.repository;

import com.nini.TaskTrackerAPI.model.Task;
import com.nini.TaskTrackerAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByUserId(long userId);
    List<User> findByFirstNameContaining(String firstName);
    List<User> findByLastNameContaining(String lastName);
    List<User> findByEmailContaining(String email);
    List<User> findByUsernameContaining(String username);

    Boolean existsByUserId(long userId);
    Boolean existsByFirstName(String firstName);
    Boolean existsByLastName(String lastName);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

    void deleteByUserId(long userId);
    void deleteByFirstName(String firstName);
    void deleteByLastName(String lastName);
    void deleteByEmail(String email);
    void deleteByUsername(String username);
}

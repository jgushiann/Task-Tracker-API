package com.example.TaskTrackerAPI.repository;

import com.example.TaskTrackerAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Boolean existsById(int id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    void deleteById(int id);
    void deleteByUsername(String username);
    void deleteByEmail(String email);
}

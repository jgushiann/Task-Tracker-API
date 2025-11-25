package com.nini.TaskTrackerAPI.repository;

import com.nini.TaskTrackerAPI.model.Task;
import com.nini.TaskTrackerAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByUserId(long userId);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    List<User> searchUsers(String firstname, String lastname, String username, String email, Long id);

    void deleteByUserId(long userId);

    boolean existsByUserId(long userId);
}

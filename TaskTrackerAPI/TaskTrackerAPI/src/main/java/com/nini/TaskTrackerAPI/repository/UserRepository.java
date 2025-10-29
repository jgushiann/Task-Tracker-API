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
    Optional<User> findByUser_id(long user_id);
    List<User> findByFirst_nameContaining(String first_name);
    List<User> findByLast_nameContaining(String last_name);
    List<User> findByEmailContaining(String email);
    List<User> findByUsernameContaining(String username);

    Boolean existsByUser_id(long user_id);
    Boolean existsByFirst_name(String first_name);
    Boolean existsByLast_name(String last_name);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

    void deleteByUser_id(long user_id);
    void deleteByFirst_name(String first_name);
    void deleteByLast_name(String last_name);
    void deleteByEmail(String email);
    void deleteByUsername(String username);
}

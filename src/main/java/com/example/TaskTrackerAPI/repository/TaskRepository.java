package com.example.TaskTrackerAPI.repository;

import com.example.TaskTrackerAPI.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findById(int id);
    Optional<Task> findByTitle(String title);
    List<Task> findByDueDate(String due_date);
    List<Task> findByUserId(int user_id);

    Boolean existsById(int id);
    Boolean existsByTitle(String title);
    Boolean existsByDueDate(String due_date);
    Boolean existsByUserId(int user_id);

    void deleteById(int id);
    void deleteByTitle(String title);
    void deleteByDueDate(String due_date);
    void deleteByUserId(int user_id);
}

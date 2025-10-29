package com.nini.TaskTrackerAPI.repository;

import com.nini.TaskTrackerAPI.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAll();

    Optional<Task> findByTaskId(long taskId);
    List<Task> findByPriority(Priority priority);
    List<Task> findByCategory(Category category);
    List<Task> findByStatus(Status status);
    List<Task> findByDueDate(LocalDate dueDate);
    List<Task> findByAssignedUser(User assignedUser);

    List<Task> findByTitleContaining(String title);
    List<Task> findByDescriptionContaining(String description);

    Boolean existsByTaskId(long taskId);
    Boolean existsByTitleContaining(String title);
    Boolean existsByDescriptionContaining(String description);
    Boolean existsByPriority(Priority priority);
    Boolean existsByCategory(Category category);
    Boolean existsByStatus(Status status);
    Boolean existsByDueDate(LocalDate dueDate);
    Boolean existsByAssignedUser(User assignedUser);

    void deleteByTaskId(long taskId);
    void deleteByTitleContaining(String title);
    void deleteByDescriptionContaining(String description);
    void deleteByPriority(Priority priority);
    void deleteByCategory(Category category);
    void deleteByStatus(Status status);
    void deleteByDueDate(LocalDate dueDate);
    void deleteByAssignedUser(User assignedUser);
}

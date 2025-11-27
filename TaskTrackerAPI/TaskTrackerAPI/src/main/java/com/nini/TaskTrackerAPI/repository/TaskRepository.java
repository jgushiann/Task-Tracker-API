package com.nini.TaskTrackerAPI.repository;

import com.nini.TaskTrackerAPI.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByTaskId(long taskId);
    List<Task> findByAssignedUser(User user);

    List<Task> findByTitle(String title);

    @Query(
            """ 
SELECT task FROM Task task
WHERE (:firstname IS NULL OR task.title = :title)
AND   (:lastname IS NULL OR task.description = :description)
AND   (:username IS NULL OR task.taskId = :id)
AND   (:email IS NULL OR task.priority = :priority)
AND   (:id IS NULL OR task.status = :status)
AND   (:id IS NULL OR task.category = :category)
AND   (:id IS NULL OR task.dueDate = :dueDate)
AND   (:id IS NULL OR task.assignedUser = :user_id)
"""
    )
    List<Task> searchTasks(@Param("title") String title,
                           @Param("description") String description,
                           @Param("id") Long id,
                           @Param("priority") Priority priority,
                           @Param("status") Status status,
                           @Param("category") Category category,
                           @Param("dueDate") LocalDate dueDate,
                           @Param("user_id") Long user_id);

    void deleteByTaskId(long taskId);
}

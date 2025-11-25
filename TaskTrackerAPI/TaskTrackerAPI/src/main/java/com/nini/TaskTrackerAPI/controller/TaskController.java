package com.nini.TaskTrackerAPI.controller;

import com.nini.TaskTrackerAPI.dto.TaskRequestDTO;
import com.nini.TaskTrackerAPI.dto.TaskResponseDTO;
import com.nini.TaskTrackerAPI.mapper.TaskMapper;
import com.nini.TaskTrackerAPI.mapper.UserMapper;
import com.nini.TaskTrackerAPI.model.*;
import com.nini.TaskTrackerAPI.service.TaskService;
import com.nini.TaskTrackerAPI.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final UserService userService;

    @Operation(summary = "Get all/specific tasks")
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> searchTasks(@RequestParam(required = false) String title,
                                                            @RequestParam(required = false) String description,
                                                            @RequestParam(required = false) Long id,
                                                            @RequestParam(required = false) Priority priority,
                                                            @RequestParam(required = false) Status status,
                                                            @RequestParam(required = false) Category category,
                                                            @RequestParam(required = false) LocalDate dueDate,
                                                            @RequestParam(required = false) Long assignedUserId){
        List<TaskResponseDTO> taskDTOs = taskService.searchTasks(title, description, id, priority, status, category, dueDate, assignedUserId);
        return ResponseEntity.status(HttpStatus.OK).body(taskDTOs);
    }

    @Operation(summary = "Get task by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable("id") Long id){
        TaskResponseDTO taskDTO = taskService.searchTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskDTO);
    }

    @Operation(summary = "Create a task")
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody @Valid TaskRequestDTO taskDTO){
        TaskResponseDTO createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @Operation(summary = "Update an existing task")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@RequestBody @Valid TaskRequestDTO updatedTaskDTO, @PathVariable Long id){
        TaskResponseDTO updatedTask = taskService.updateTask(updatedTaskDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
    }

    @Operation(summary = "Get all the tasks assigned to the user")
    @GetMapping("/user/{user_id}")
    public List<TaskResponseDTO> getTasksForUser(@PathVariable Long user_id){
        User user = userService.searchUserByUserId(user_id);
        return taskService.getTasksByAssignedUser(user);
    }

    @Operation(summary = "Delete a task by its ID")
    @DeleteMapping("/{task_id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long task_id) {
        taskService.deleteTask(task_id);
        return ResponseEntity.noContent().build();
    }
}

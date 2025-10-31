package com.nini.TaskTrackerAPI.controller;

import com.nini.TaskTrackerAPI.model.*;
import com.nini.TaskTrackerAPI.service.TaskService;
import com.nini.TaskTrackerAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Task> searchTasks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String description,
                                  @RequestParam(required = false) Long id,
                                  @RequestParam(required = false) Priority priority,
                                  @RequestParam(required = false) Status status,
                                  @RequestParam(required = false) Category category,
                                  @RequestParam(required = false) LocalDate dueDate,
                                  @RequestParam(required = false) Long user_id) throws Exception{
        return taskService.searchTasks(title, description, id, priority, status, category,dueDate,user_id);
    }

    @GetMapping("/{task_id}")
    public Task getTask(@PathVariable("task_id") Long task_id) throws Exception{
        return taskService.searchTaskById(task_id);
    }

    @PostMapping
    public void createTask(@RequestBody Task task) throws Exception{
        taskService.createTask(task);
    }

    @PutMapping("/{task_id}")
    public void updateTask(@RequestBody Task updatedTask, @PathVariable Long task_id) throws Exception{
        taskService.updateTask(updatedTask, task_id);
    }

    @GetMapping("/user/{user_id}")
    public List<Task> getTasksForUser(@PathVariable Long user_id) throws Exception{
        User user = userService.searchUserByUserId(user_id);
        return taskService.getTasksByAssignedUser(user);
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable Long task_id) throws Exception{
        taskService.deleteTask(task_id);
    }
}

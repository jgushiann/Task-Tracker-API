package com.nini.TaskTrackerAPI.controller;

import com.nini.TaskTrackerAPI.model.*;
import com.nini.TaskTrackerAPI.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

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

    @DeleteMapping
    public void deleteTasks(@RequestParam(required = false) String title,
                            @RequestParam(required = false) String description,
                            @RequestParam(required = false) Long id,
                            @RequestParam(required = false) Priority priority,
                            @RequestParam(required = false) Status status,
                            @RequestParam(required = false) Category category,
                            @RequestParam(required = false) LocalDate dueDate,
                            @RequestParam(required = false) Long user_id) throws Exception{
        taskService.deleteTasks(title, description, id, priority, status, category,dueDate,user_id);

    }
}

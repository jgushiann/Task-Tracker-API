package com.nini.TaskTrackerAPI.dto;

import com.nini.TaskTrackerAPI.model.Category;
import com.nini.TaskTrackerAPI.model.Priority;
import com.nini.TaskTrackerAPI.model.Status;
import lombok.Data;

import java.time.LocalDate;
@Data
public class TaskResponseDTO{
        String title;
        String description;
        Long assignedUser;
        Priority priority;
        Category category;
        Status status;
        LocalDate dueDate;
}
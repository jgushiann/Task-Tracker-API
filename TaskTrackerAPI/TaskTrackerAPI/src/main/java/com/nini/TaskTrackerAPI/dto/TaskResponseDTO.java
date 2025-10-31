package com.nini.TaskTrackerAPI.dto;

import com.nini.TaskTrackerAPI.model.Category;
import com.nini.TaskTrackerAPI.model.Priority;
import com.nini.TaskTrackerAPI.model.Status;

import java.time.LocalDate;

public record TaskResponseDTO(
        String taskTitle,
        String taskDescription,
        Long taskAssignedUserId,
        Priority taskPriority,
        Category taskCategory,
        Status taskStatus,
        LocalDate taskDueDate
) {}
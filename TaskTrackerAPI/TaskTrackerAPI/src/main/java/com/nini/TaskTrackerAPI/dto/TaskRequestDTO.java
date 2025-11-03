package com.nini.TaskTrackerAPI.dto;
import com.nini.TaskTrackerAPI.model.Category;
import com.nini.TaskTrackerAPI.model.Priority;
import com.nini.TaskTrackerAPI.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {

    @NotBlank
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String taskTitle;

    private String taskDescription;

    @NotNull(message = "Task status must be provided")
    private Status taskStatus;

    @NotNull(message = "Task category must be provided")
    private Category taskCategory;

    @NotNull(message = "Task priority must be provided")
    private Priority taskPriority;

    @NotNull(message = "Task deadline must be provided")
    private LocalDate taskDueDate;

    @NotNull(message = "Task must be assigned to the User")
    private Long taskAssignedUserId;
}

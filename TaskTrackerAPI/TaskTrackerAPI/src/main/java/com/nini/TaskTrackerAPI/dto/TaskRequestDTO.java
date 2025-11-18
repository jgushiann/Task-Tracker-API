package com.nini.TaskTrackerAPI.dto;
import com.nini.TaskTrackerAPI.model.Category;
import com.nini.TaskTrackerAPI.model.Priority;
import com.nini.TaskTrackerAPI.model.Status;
import com.nini.TaskTrackerAPI.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {

    @NotBlank
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    private String description;

    @NotNull
    private Long id;

    @NotNull(message = "Task status must be provided")
    private Status status;

    @NotNull(message = "Task category must be provided")
    private Category category;

    @NotNull(message = "Task priority must be provided")
    private Priority priority;

    @NotNull(message = "Task deadline must be provided")
    private LocalDate dueDate;

    @NotNull(message = "Task must be assigned to the User")
    private Long assignedUserId;
}

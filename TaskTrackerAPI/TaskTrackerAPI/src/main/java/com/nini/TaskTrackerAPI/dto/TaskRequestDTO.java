package com.nini.TaskTrackerAPI.dto;
import com.nini.TaskTrackerAPI.model.Category;
import com.nini.TaskTrackerAPI.model.Priority;
import com.nini.TaskTrackerAPI.model.Status;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {
    private String taskTitle;
    private String taskDescription;
    private Status taskStatus;
    private Category taskCategory;
    private Priority taskPriority;
    private LocalDate taskDueDate;
    private Long taskAssignedUserId;
}

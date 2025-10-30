package com.nini.TaskTrackerAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false, unique = true)
    private long taskId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "priority")
    private Priority priority;

    @Column(name = "category")
    private Category category;

    @Column(name = "status")
    private Status status;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User assignedUser;

    public Task() {}

    public Task(String title, String description, Priority priority, Category category, Status status, LocalDate dueDate, User assignedUser) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.status = status;
        this.dueDate = dueDate;
        this.assignedUser = assignedUser;
    }
}

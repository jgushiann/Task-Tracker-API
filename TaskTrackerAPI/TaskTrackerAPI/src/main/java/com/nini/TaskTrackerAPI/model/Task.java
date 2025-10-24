package com.nini.TaskTrackerAPI.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long task_id;

    private String title;

    private String description;

    private Priority priority;

    private Category category;

    private Status status;

    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User assignedUser;

    public Task() {}

    public Task(String title, String description, Priority priority, Category category, Status status, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.status = status;
        this.dueDate = dueDate;
    }

    public long getId() {
        return task_id;
    }

    public void setId(long id) {
        this.task_id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }
}

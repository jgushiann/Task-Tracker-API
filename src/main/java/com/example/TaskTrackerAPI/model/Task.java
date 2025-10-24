package com.example.TaskTrackerAPI.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    private String title;

    private String description;

    private String dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    public Task(){}

    public Task(String title, String description, String due_date, User user) {
        this.title = title;
        this.description = description;
        this.dueDate = due_date;
        this.user = user;
    }

    public int  getTaskId() {
        return taskId;
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

    public String getDue_date() {
        return dueDate;
    }

    public void setDue_date(String due_date) {
        this.dueDate = due_date;
    }


}

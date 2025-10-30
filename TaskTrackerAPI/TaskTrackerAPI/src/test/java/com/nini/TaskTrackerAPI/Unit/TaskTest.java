package com.nini.TaskTrackerAPI.Unit;

import com.nini.TaskTrackerAPI.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.Month;

public class TaskTest {

    private User testUser;
    private Task testTask;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testTask = new Task();
        testTask.setTaskId(1);
        testTask.setTitle("Test Title");
        testTask.setDescription("Test Description");
        testTask.setPriority(Priority.HIGH);
        testTask.setCategory(Category.PERSONAL);
        testTask.setStatus(Status.COMPLETED);
        testTask.setDueDate(LocalDate.of(2026, Month.JANUARY, 1));
        testTask.setAssignedUser(testUser);
    }

    @AfterEach
    public void tearDown() {
        testUser = null;
        testTask = null;
    }

    @Test
    void getId() {
        assertEquals(1, testTask.getTaskId());
    }

    @Test
    void setId(){
        testTask.setTaskId(2);
        assertEquals(2, testTask.getTaskId());
    }

    @Test
    void getTitle() {
        assertEquals("Test Title", testTask.getTitle());
    }

    @Test
    void setTitle() {
        testTask.setTitle("Test_Title");
        assertEquals("Test_Title", testTask.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("Test Description", testTask.getDescription());
    }

    @Test
    void setDescription() {
        testTask.setDescription("Test_Description");
        assertEquals("Test_Description", testTask.getDescription());
    }

    @Test
    void getPriority() {
        assertEquals(Priority.HIGH, testTask.getPriority());
    }

    @Test
    void setPriority() {
        testTask.setPriority(Priority.LOW);
        assertEquals(Priority.LOW, testTask.getPriority());
    }

    @Test
    void getCategory() {
        assertEquals(Category.PERSONAL, testTask.getCategory());
    }

    @Test
    void setCategory() {
        testTask.setCategory(Category.WORK);
        assertEquals(Category.WORK, testTask.getCategory());
    }

    @Test
    void getStatus() {
        assertEquals(Status.COMPLETED, testTask.getStatus());
    }

    @Test
    void setStatus() {
        testTask.setStatus(Status.IN_PROGRESS);
        assertEquals(Status.IN_PROGRESS, testTask.getStatus());
    }

    @Test
    void getDueDate() {
        assertEquals(LocalDate.of(2026, Month.JANUARY, 1), testTask.getDueDate());
    }

    @Test
    void setDueDate() {
        testTask.setDueDate(LocalDate.of(2025, Month.DECEMBER, 1));
        assertEquals(LocalDate.of(2025, Month.DECEMBER, 1), testTask.getDueDate());
    }

    @Test
    void getAssignedUser() {
        assertEquals(testUser, testTask.getAssignedUser());
    }

    @Test
    void setAssignedUser() {
        User user = new User();
        testTask.setAssignedUser(user);
        assertEquals(user, testTask.getAssignedUser());
    }
}

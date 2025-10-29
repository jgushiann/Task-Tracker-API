package com.nini.TaskTrackerAPI.Unit;

import com.nini.TaskTrackerAPI.model.Task;
import com.nini.TaskTrackerAPI.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User testUser;
    private Task testTask;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testTask = new Task();
        testUser.setUserId(1);
        testUser.setFirstName("Nini");
        testUser.setLastName("Smith");
        testUser.setEmail("nini.smith@example.com");
        testUser.setUsername("ninismith");
        testUser.setPassword("ninismith123");
        testUser.setTasks(List.of(testTask));
    }

    @AfterEach
    void tearDown() {
        testUser = null;
        testTask = null;
    }

    @Test
    void getId() {
        assertEquals(1, testUser.getUserId());
    }

    @Test
    void setId() {
        testUser.setUserId(2);
        assertEquals(2, testUser.getUserId());
    }

    @Test
    void getFirst_name() {
        assertEquals("Nini", testUser.getFirstName());
    }

    @Test
    void setFirst_name() {
        testUser.setFirstName("Nini_Smith");
        assertEquals("Nini_Smith", testUser.getFirstName());
    }

    @Test
    void getLast_name() {
        assertEquals("Smith", testUser.getLastName());
    }

    @Test
    void setLast_name() {
        testUser.setLastName("Nini_Smith");
        assertEquals("Nini_Smith", testUser.getLastName());
    }

    @Test
    void getEmail() {
        assertEquals("nini.smith@example.com", testUser.getEmail());
    }

    @Test
    void setEmail() {
        testUser.setEmail("smith@example.com");
        assertEquals("smith@example.com",testUser.getEmail());
    }

    @Test
    void getUsername() {
        assertEquals("ninismith", testUser.getUsername());
    }

    @Test
    void setUsername() {
        testUser.setUsername("nini.smith");
        assertEquals("nini.smith", testUser.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("ninismith123", testUser.getPassword());
    }

    @Test
    void setPassword() {
        testUser.setPassword("nini.smith123");
        assertEquals("nini.smith123", testUser.getPassword());
    }

    @Test
    void getTasks() {
        assertEquals(List.of(testTask), testUser.getTasks());
    }

    @Test
    void setTasks() {
        List<Task> tasks = List.of(new Task());
        testUser.setTasks(tasks);
        assertEquals(tasks, testUser.getTasks());
    }
}
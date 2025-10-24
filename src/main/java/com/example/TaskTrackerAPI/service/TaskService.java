package com.example.TaskTrackerAPI.service;

import com.example.TaskTrackerAPI.model.Task;
import com.example.TaskTrackerAPI.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task getTaskById(int id){
        return taskRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Task not found"));
    }

    public Task getTaskByTitle(String title){
        return taskRepository.findByTitle(title)
                .orElseThrow(()-> new RuntimeException("Task not found"));
    }

    public List<Task> getTaskByDueDate(String due_date){
        return  taskRepository.findByDueDate(due_date);
    }

    public List<Task> getTaskByUserId(int user_id){
        return taskRepository.findByUserId(user_id);
    }

    public void deleteTaskById(int id) throws Exception{
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    public void deleteTaskByTitle(String title) throws Exception{
        if(taskRepository.existsByTitle(title)){
            taskRepository.deleteByTitle(title);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    public void deleteTaskByDueDate(String due_date) throws Exception{
        if(taskRepository.existsByDueDate(due_date)){
            taskRepository.deleteByDueDate(due_date);
        }else{
            throw new RuntimeException("Task does not exist");
        }
    }

    public void deleteTaskByUserId(int user_id) throws Exception{
        if(taskRepository.existsByUserId(user_id)){
            taskRepository.deleteByUserId(user_id);
        }else{
            throw new RuntimeException("Tasks does not exist");
        }
    }

}

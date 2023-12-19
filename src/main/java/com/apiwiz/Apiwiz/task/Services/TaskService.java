package com.apiwiz.Apiwiz.task.Services;

import com.apiwiz.Apiwiz.task.Entities.Task;
import com.apiwiz.Apiwiz.task.Entities.User;
import com.apiwiz.Apiwiz.task.Enums.TaskStatus;
import com.apiwiz.Apiwiz.task.Repositories.TaskRepository;
import com.apiwiz.Apiwiz.task.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> getTasksForUser(int userId) {
        User user = userRepository.findById(userId).get();

        List<Task> tasks = user.getUserTasks();
        return tasks;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Task createTask(Task taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setStatus(TaskStatus.PENDING);

        // Save the task to the database
        return taskRepository.save(task);
    }

    public Task updateTask(int taskId, Task modifiedTask) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Task not found"));;
        
        task.setTitle(modifiedTask.getTitle());
        task.setDescription(modifiedTask.getDescription());
        task.setDueDate(modifiedTask.getDueDate());
        task.setStatus(modifiedTask.getStatus());
        
        return taskRepository.save(task);

    }

    public void deleteTask(int taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));

        // Delete the task from the database
        taskRepository.delete(task);
    }
}
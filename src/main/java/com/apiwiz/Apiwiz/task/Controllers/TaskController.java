package com.apiwiz.Apiwiz.task.Controllers;

import com.apiwiz.Apiwiz.task.Entities.Task;
import com.apiwiz.Apiwiz.task.Services.TaskService;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/apiwiz")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/getTasks")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Task>> getTasksForUser(@RequestParam("userId") int userId, Authentication authentication) {
        // if role is admin then getAll the tasks
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN"));

        List<Task> tasks;
        if (isAdmin) {
            tasks = taskService.getAllTasks();
        } else {
            tasks = taskService.getTasksForUser(userId);
        }

        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/createTask")
    @PreAuthorize("hasRole('USER'")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("updateTask/{taskId}")
    @PreAuthorize("hasRole('USER'")
    public ResponseEntity<Task> updateTask(@PathVariable("taskId") int taskId, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(taskId, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("deleteTask/{taskId}")
    @PreAuthorize("hasRole('USER'")
    public ResponseEntity<?> deleteTask(@PathVariable("taskId") int taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }

}
package com.taskmanager.controller;

import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    // GET /api/tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    
    // GET /api/tasks/pending
    @GetMapping("/pending")
    public ResponseEntity<List<Task>> getPendingTasks() {
        return ResponseEntity.ok(taskService.getPendingTasks());
    }
    
    // GET /api/tasks/completed
    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasks() {
        return ResponseEntity.ok(taskService.getCompletedTasks());
    }
    
    // GET /api/tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    // POST /api/tasks
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task created = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    // PUT /api/tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updated = taskService.updateTask(id, task);
        return ResponseEntity.ok(updated);
    }
    
    // PATCH /api/tasks/{id}/complete
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long id) {
        Task completed = taskService.completeTask(id);
        return ResponseEntity.ok(completed);
    }
    
    // DELETE /api/tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
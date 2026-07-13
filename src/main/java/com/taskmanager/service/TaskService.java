package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    // Listar todas
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    // Listar pendentes
    public List<Task> getPendingTasks() {
        return taskRepository.findByCompletedFalse();
    }
    
    // Listar concluídas
    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompletedTrue();
    }
    
    // Buscar por ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    
    // Criar
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }
    
    // Atualizar
    public Task updateTask(Long id, Task taskDetails) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setCompleted(taskDetails.getCompleted());
            task.setUpdatedAt(LocalDateTime.now());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task não encontrada"));
    }
    
    // Deletar
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    
    // Marcar como concluída
    public Task completeTask(Long id) {
        return taskRepository.findById(id).map(task -> {
            task.setCompleted(true);
            task.setUpdatedAt(LocalDateTime.now());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task não encontrada"));
    }
}
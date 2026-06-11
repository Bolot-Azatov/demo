package com.example.demo.todolist.controller;

import com.example.demo.todolist.model.Task;
import com.example.demo.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // GET: http://localhost:8080/api/tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // GET: http://localhost:8080/api/tasks/5
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        // ResponseEntity позволяет гибко настраивать HTTP-статусы ответа
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    // POST: http://localhost:8080/api/tasks
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        // @RequestBody автоматически конвертирует пришедший JSON в объект Task
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED); // Возвращаем статус 201 Created
    }

    // PUT: http://localhost:8080/api/tasks/5
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    // DELETE: http://localhost:8080/api/tasks/5
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build(); // Возвращаем статус 204 No Content
    }
}

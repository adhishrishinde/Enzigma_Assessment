package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Task;
import com.app.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
	
	 @Autowired
	 private TaskService taskService;

	    public TaskController(TaskService taskService) {
	        this.taskService = taskService;
	    }
	    
	 	@GetMapping
	    public List<Task> getAllTasks(@RequestParam(required = false) String assignedTo) {
	        if (assignedTo != null) {
	            return taskService.searchTasks(assignedTo);
	        }
	        return taskService.getAllTasks();
	    }
	    
	 	
	    @GetMapping("/tasks")
	    public Page<Task> getTasks(@RequestParam(defaultValue = "0") int page, 
	                               @RequestParam(defaultValue = "5") int size) {
	        return taskService.getAllTasks(PageRequest.of(page, size));
	    }

  
	    @PostMapping
	    public Task createTask(@RequestBody Task task) {
	        return taskService.createTask(task);
	    }


	    @PutMapping("/{id}")
	    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
	        Task updatedTask = taskService.updateTask(id, taskDetails);
	        return updatedTask != null ? ResponseEntity.ok(updatedTask) : ResponseEntity.notFound().build();
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
	        taskService.deleteTask(id);
	        return ResponseEntity.noContent().build();
	    }
	    

}

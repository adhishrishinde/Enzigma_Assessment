package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.entity.Task;


public interface TaskService {
	
	 List<Task> getAllTasks();
	 Task getTaskById(Long id);
	 Task createTask(Task task);
	 Task updateTask(Long id, Task task);
	 void deleteTask(Long id);
	 Page<Task> getAllTasks(Pageable pageable);
	 List<Task> searchTasks(String assignedTo);
}


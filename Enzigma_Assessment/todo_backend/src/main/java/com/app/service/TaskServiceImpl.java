package com.app.service;

import com.app.dao.TaskDao;
import com.app.entity.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	 @Autowired
	 private TaskDao taskDao;
	
	 @Override
	 public List<Task> getAllTasks() {
	     return taskDao.findAll();
	 }
	
	 @Override
	 public Task getTaskById(Long id) {
	     Optional<Task> task = taskDao.findById(id);
	     if (task.isPresent()) {
	         return task.get();
	     } else {
	         throw new RuntimeException("Task not found with id: " + id);
	     }
	 }
	
	 @Override
	 public Task createTask(Task task) {
	     return taskDao.save(task);
	 }
	
	 @Override
	 public Task updateTask(Long id, Task updatedTask) {
		 return taskDao.findById(id).map(task->{
	     task.setAssignedTo(updatedTask.getAssignedTo());
	     task.setStatus(updatedTask.getStatus());
	     task.setDueDate(updatedTask.getDueDate());
	     task.setPriority(updatedTask.getPriority());
	     task.setComments(updatedTask.getComments());
	     return taskDao.save(task);
		 })
		.orElseThrow(()->new RuntimeException("Task not found with id:"+id));
	 }
	 
	 public Page<Task> getAllTasks(Pageable pageable) {
	        return taskDao.findAll(pageable);  // The repository provides built-in pagination support
	    }
	 
	
	 public void deleteTask(Long id)
	 {
		 taskDao.deleteById(id);
	 }
	 
	 public List<Task> searchTasks(String assignedTo) {
	        return taskDao.findByAssignedToContainingIgnoreCase(assignedTo);
	    }
	 
	
}


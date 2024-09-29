package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Task;

public interface TaskDao extends JpaRepository<Task, Long>{

    List<Task> findByAssignedToContainingIgnoreCase(String assignedTo);

}

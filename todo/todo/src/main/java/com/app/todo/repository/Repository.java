package com.app.todo.repository;

import com.app.todo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Task, Integer> {
}

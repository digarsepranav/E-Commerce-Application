package com.app.todo.service;

import com.app.todo.dto.TaskResponse;
import com.app.todo.entity.Task;
import com.app.todo.repository.Repository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class Service {

    private final Repository repository;

    public void addTask(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalArgumentException("Task is not defined");
        }
        Task task = new Task();
        task.setS(s);
        repository.save(task);
    }

    public List<TaskResponse> fetchAllTasks() {
        List<Task> task = repository.findAll();

        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task1 : task) {
            taskResponses.add(new TaskResponse(task1.getS()));
        }
        return taskResponses;
    }
}

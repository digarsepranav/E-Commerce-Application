package com.app.todo.controller;

import com.app.todo.dto.TaskResponse;
import com.app.todo.entity.Task;
import com.app.todo.repository.Repository;
import com.app.todo.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final Service service;

    // create a task : will be a string and have to get it from the user
    @PostMapping("api/addTask")
    public ResponseEntity<String> createTask(@RequestBody String s) {
        service.addTask(s);
        return ResponseEntity.status(HttpStatus.CREATED).body("Task is created! ");
    }

    // get the tasks :
    @GetMapping("api/fetchTask")
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok(service.fetchAllTasks());
    }

    // fetch a task by id :

}

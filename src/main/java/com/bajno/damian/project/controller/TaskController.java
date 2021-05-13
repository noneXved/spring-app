package com.bajno.damian.project.controller;

import com.bajno.damian.project.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    ResponseEntity<?> readAllTasks() {
        logger.warn("Exposing all the tasks!");
        return ResponseEntity.ok(taskRepository.findAll());
    }
//    @RequestMapping(method = RequestMethod.GET, path = "/tasks")
//    ResponseEntity<List<Task>> readAllTasks() {
//        logger.warn("Exposing all the tasks!");
//        return ResponseEntity.ok(taskRepository.findAll());
//    }
}

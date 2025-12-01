package com.project.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;

    @Autowired
    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    public void addTask(Tasks task) {
        tasksRepository.save(task);
    }

    public void deleteTaskById(int id) {
        tasksRepository.deleteById(id);
    }
}

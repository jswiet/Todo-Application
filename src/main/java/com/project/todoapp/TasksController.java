package com.project.todoapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TasksController {
    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }
    @GetMapping("/")
    public String getAllTasks(Model model) {
        List<Tasks> tasks = tasksService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "index";
    }
    @PostMapping("/add")
    public String addTask(@RequestParam String task) {
        tasksService.addTask(new Tasks(task));
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam int id) {
        tasksService.deleteTaskById(id);
        return "redirect:/";
    }

}

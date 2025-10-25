package com.project.todoapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TodoAppIntegrationTest extends AbstractTestConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TasksRepository tasksRepository;

    @BeforeEach
    void setup() {
        tasksRepository.deleteAll();
    }

    @Test
    void canGetAllTasks() throws Exception {
        //given
        tasksRepository.saveAll(List.of(new Tasks("First task"), new Tasks("Second task")));

        //then
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(view().name("index"))
               .andExpect(model().attributeExists("tasks"))
               .andExpect(model().attribute("tasks", hasItem(hasProperty("task", is("First task")))))
               .andExpect(model().attribute("tasks", hasItem(hasProperty("task", is("Second task")))));
    }

    @Test
    void canAddTask() throws Exception {
        //given
        tasksRepository.save(new Tasks("First task"));

        //then
        mockMvc.perform(post("/add").param("task", "First task"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/"));

        List<Tasks> tasks = tasksRepository.findAll();
        assertThat(tasks, hasItem(hasProperty("task", is("First task"))));

    }

    @Test
    void canDeleteTask() throws Exception {
        //given
        tasksRepository.saveAll(List.of(new Tasks("First task"), new Tasks("Second task")));

        //then
        mockMvc.perform(post("/delete").param("id", "1"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/"));

        List<Tasks> tasks = tasksRepository.findAll();
        assertThat(tasks, not(hasItem(hasProperty("task", is("First task")))));
        assertThat(tasks, hasSize(1));
    }

}

package com.project.todoapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TasksServiceTest {

    @Mock
    private TasksRepository tasksRepository;

    @InjectMocks
    private TasksService UTtasksService;

    @Test
    void canGetAllTasks() {
        //given
        List<Tasks> testTasks = List.of(new Tasks("First task"), new Tasks("Second task"));
        //when
        when(tasksRepository.findAll()).thenReturn(testTasks);
        List<Tasks> result = UTtasksService.getAllTasks();
        //then
        assertThat(result).isEqualTo(testTasks);
        verify(tasksRepository).findAll();

    }
    @Test
    void addTask() {
        //given
        Tasks task = new Tasks("Some new task");
        //when
        UTtasksService.addTask(task);
        //then
        verify(tasksRepository).save(task);
    }

    @Test
    void deleteTaskById() {
        //given
        int id = 2;
        //when
        UTtasksService.deleteTaskById(2);
        //then
        verify(tasksRepository).deleteById(2);
    }
}
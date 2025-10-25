package com.project.todoapp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TasksRepository extends JpaRepository<Tasks, Integer> {
}

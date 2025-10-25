package com.project.todoapp;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "task")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String task;

    public Tasks() {
    }

    public Tasks(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||
                getClass() != o.getClass())
            return false;
        Tasks tasks = (Tasks) o;
        return id == tasks.id &&
                Objects.equals(task, tasks.task);
    }

    @Override
    public String toString() {
        return "Tasks{" + "id=" + id +
                ", task='" + task + '\'' + '}';
    }
}

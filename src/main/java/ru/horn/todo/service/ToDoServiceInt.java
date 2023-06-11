package ru.horn.todo.service;

import ru.horn.todo.dto.TaskDto;

import java.util.List;

public interface ToDoServiceInt {
    List<TaskDto> getTasks();
    void addNewTask(String name, String description, Boolean done);
    void editTask(Long id, String name, String description, Boolean done);

}

package ru.horn.todo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.horn.todo.dto.TaskDto;
import ru.horn.todo.entity.Task;
import ru.horn.todo.repo.TaskRepository;
import ru.horn.todo.service.ToDoServiceInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements ToDoServiceInt {
    private TaskRepository taskRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TodoServiceImpl(TaskRepository repo, JdbcTemplate jdbcTemplate) {
        this.taskRepository = repo;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<TaskDto> getTasks() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks.stream()
                .map(task -> new TaskDto(
                        task.getId(),
                        task.getName(),
                        task.getDescription(),
                        task.getDone()
                )).collect(Collectors.toList());
    }

    @Override
    public void addNewTask(String name, String description, Boolean done) {
        Task task = new Task(name, description, done);
        taskRepository.save(task);
    }

    public void removeTask(Long id) {
        Boolean exists = taskRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Element does not exists");
        }

        taskRepository.deleteById(id);
    }

    @Override
    public void editTask(Long id, String name, String description, Boolean done) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Task with id " + id + " not found!"));

        if (name != null && name.length() > 0 && !Objects.equals(task.getName(), name)) {
            task.setName(name);
            task.setDescription(description);
            task.setDone(done);

            taskRepository.save(task);

        }
    }
}

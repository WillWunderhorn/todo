package ru.horn.todo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.horn.todo.entity.Task;
import ru.horn.todo.repo.TaskRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TodoService {
    static TaskRepository taskRepository;
    JdbcTemplate jdbcTemplate;

    public TodoService(TaskRepository repo, JdbcTemplate jdbcTemplate) {
        this.taskRepository = repo;
        this.jdbcTemplate = jdbcTemplate;
    }

    public static List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public List<Task> addNewTask(Task task) {
        taskRepository.save(task);
        return taskRepository.findAll();
    }

    public void removeTask(Long id){
        taskRepository.findById(id);

        boolean exists = taskRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("Element does not exists");
        }

        taskRepository.deleteById(id);
    }

    public void editTask(Long id, String name, String description, boolean done) {
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

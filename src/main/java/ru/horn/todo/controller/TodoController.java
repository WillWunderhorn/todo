package ru.horn.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.horn.todo.entity.Task;
import ru.horn.todo.repo.TaskRepository;
import ru.horn.todo.service.TodoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TodoController {
    TaskRepository taskRepository;
    TodoService todoService;

    @Autowired
    public TodoController(TaskRepository taskRepository, TodoService todoService) {
        this.taskRepository = taskRepository;
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String getMainPage(Model model) {
        List<Task> tasks = todoService.getTasks();
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        return "addPage";
    }

    @PostMapping("/")
    @ResponseBody
    public List<Task> addNewTask(@RequestBody Task task) {
        todoService.addNewTask(task);
        return taskRepository.findAll();
    }

    @PostMapping("/add")
    public String addNewTaskFromForm(
            @RequestParam(defaultValue = "New task") String name,
            @RequestParam String description,
            @RequestParam(defaultValue = "false") boolean done,
            Model model
    ) {
        Task task = new Task(name, description, done);
        taskRepository.save(task);
        return "redirect:/";
    }

    @PostMapping(path = "/delete/{id}")
    public String removeTask(@PathVariable(value = "id") long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(task);
        return "redirect:/";
    }

    @GetMapping(path = "/{id}/edit")
    public String editTask(@PathVariable(value = "id") long id, Model model) {
        if (!taskRepository.existsById(id)){
            return "redirect:/";
        }
        Optional<Task> task = taskRepository.findById(id);
        ArrayList<Task> tasks = new ArrayList<>();
        task.ifPresent(tasks::add);
        model.addAttribute("task", tasks);
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String updateTask(
            @PathVariable(value = "id") long id,
            @RequestParam(defaultValue = "New task") String name,
            @RequestParam String description,
            @RequestParam(defaultValue = "false") boolean done,
            Model model
    ) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setName(name);
        task.setDescription(description);
        task.setDone(done);
        taskRepository.save(task);
        return "redirect:/";
    }
}

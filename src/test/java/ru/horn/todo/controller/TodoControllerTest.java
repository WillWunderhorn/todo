package ru.horn.todo.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.horn.todo.repo.TaskRepository;
import ru.horn.todo.service.impl.TodoServiceImpl;

class TodoControllerTest {

    @Mock
    private TodoServiceImpl todoService;
    private TaskRepository taskRepository;

    @InjectMocks
    private TodoController todoController;
    private MockMvc mockMvc;

    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

//    @Test
//    public void testGetMainPage() throws Exception {
//        List<Task> tasks = new ArrayList<>();
//        tasks.add(new Task("Task 1", "Description 1", false));
//        tasks.add(new Task("Task 2", "Description 2", true));
//        when(TodoServiceImpl.getTasks()).thenReturn(tasks);
//
//        mockMvc.perform(get("/"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("index"))
//                .andExpect(model().attributeExists("tasks"))
//                .andExpect(model().attribute("tasks", tasks));
//
//        verify(todoService).getTasks();
//    }

//    @Test
//    void getAddPage() {
//    }
//
//    @Test
//    void addNewTask() {
//    }
//
//    @Test
//    void addNewTaskFromForm() {
//    }
//
//    @Test
//    void removeTask() {
//    }
//
//    @Test
//    void editTask() {
//    }
//
//    @Test
//    void updateTask() {
//    }
}
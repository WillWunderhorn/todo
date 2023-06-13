package ru.horn.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.horn.todo.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}

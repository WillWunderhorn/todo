package ru.horn.todo.mapper;
import org.mapstruct.Mapper;
import ru.horn.todo.dto.TaskDto;
import ru.horn.todo.entity.Task;

@Mapper
public interface TaskMapper {
    TaskDto toTaskDto(Task task);
}

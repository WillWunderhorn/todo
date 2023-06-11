package ru.horn.todo.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private Boolean done;

    public TaskDto(String name, String description, Boolean done) {
        this.name = name;
        this.description = description;
        this.done = done;
    }
}

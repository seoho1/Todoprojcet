package com.example.todoprojcet.dto;

import com.example.todoprojcet.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class TodoResponseDto {

    private Long id;
    private String todo;
    private String author;
    private int password;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.todo = todo.getTodo();
        this.password = todo.getPassword();
        this.author =todo.getAuthor();

    }
}

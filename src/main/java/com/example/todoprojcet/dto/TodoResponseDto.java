package com.example.todoprojcet.dto;

import com.example.todoprojcet.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class TodoResponseDto {

    private Long id;
    private String todo;
    private String author;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;





    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.todo = todo.getTodo();
        this.author =todo.getAuthor();


    }
}

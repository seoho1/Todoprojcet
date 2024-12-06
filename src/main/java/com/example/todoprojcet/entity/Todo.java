package com.example.todoprojcet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Todo {

    private Long id;
    private String todo;
    private String author;
    private String password;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Todo(String todo, String author, String password){
        this.todo = todo;
        this.password = password;
        this.author = author;
    }

    public void update(String todo) {
        this.todo = todo;
    }
}

package com.example.todoprojcet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Todo {

    private Long id;
    private String todo;
    private String author;
    private int password;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Todo(String todo, String author, int password){
        this.todo = todo;
        this.password = password;
        this.author = author;
    }

    public void update(String todo) {
        this.todo = todo;
    }
}

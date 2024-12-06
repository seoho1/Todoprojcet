package com.example.todoprojcet.dto;


import com.example.todoprojcet.entity.Todo;
import lombok.Getter;

@Getter
public class TodoRequestDto {

    private String todo;
    private String author;
    private String password;

}

package com.example.todoprojcet.service;

import com.example.todoprojcet.dto.TodoRequestDto;
import com.example.todoprojcet.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {

    TodoResponseDto saveTodo(TodoRequestDto dto);

    List<TodoResponseDto> findAllTodos();

    TodoResponseDto findTodoById(Long id);

    List<TodoResponseDto>  findTodoByAuthor(String author);

    TodoResponseDto updateTodo(Long id, String todo, String password);

    void deleteTodo(Long id);

    List<TodoResponseDto> findTodoUpdatedDate();
}

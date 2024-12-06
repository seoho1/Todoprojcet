package com.example.todoprojcet.service;

import com.example.todoprojcet.dto.TodoRequestDto;
import com.example.todoprojcet.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {

    TodoResponseDto saveTodo(TodoRequestDto dto);

    List<TodoResponseDto> findAllTodos();

    TodoResponseDto findTodoById(Long id);

    TodoResponseDto updateTodo(Long id, String todo);

    void deleteTodo(Long id);
}

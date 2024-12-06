package com.example.todoprojcet.repository;

import com.example.todoprojcet.dto.TodoResponseDto;
import com.example.todoprojcet.entity.Todo;

import java.util.List;

public interface TodoRepository {

    TodoResponseDto saveTodo(Todo todo);

    List<TodoResponseDto> findAllTodos();

    Todo findTodoById(Long id);

    void deleteTodo(Long id);

}

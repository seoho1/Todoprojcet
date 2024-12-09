package com.example.todoprojcet.repository;

import com.example.todoprojcet.dto.TodoResponseDto;
import com.example.todoprojcet.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {

    TodoResponseDto saveTodo(Todo todo);

    List<TodoResponseDto> findAllTodos();

    Optional<Todo> findTodoById(Long id);

    int deleteTodo(Long id, String password);

    Todo findTodoByIdOrElseThrow(Long id);

    int updateTodo(Long id, String title, String password);

    List<TodoResponseDto> findTodoByAuthor(String author);

    List<TodoResponseDto> findTodoByUpdatedDate();
}

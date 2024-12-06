//package com.example.todoprojcet.repository;
//
//import com.example.todoprojcet.dto.TodoResponseDto;
//import com.example.todoprojcet.entity.Todo;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//public class TodoRepositoryImpl implements TodoRepository {
//
//    private final Map<Long, Todo> todoList = new HashMap<>();
//
//    @Override
//    public Todo saveTodo(Todo todo) {
//
//        long todoId = todoList.isEmpty() ? 1 : Collections.max(todoList.keySet()) + 1;
//        todo.setId(todoId);
//
//        todoList.put(todoId, todo);
//
//        return todo;
//
//    }
//
//    @Override
//    public List<TodoResponseDto> findAllTodos() {
//
//        List<TodoResponseDto> allTodos = new ArrayList<>();
//
//        for (Todo todo : todoList.values()) {
//            TodoResponseDto responseDto = new TodoResponseDto(todo);
//            allTodos.add(responseDto);
//        }
//        return allTodos;
//    }
//
//    @Override
//    public Todo findTodoById(Long id) {
//        return todoList.get(id);
//    }
//
//    @Override
//    public void deleteTodo(Long id) {
//        todoList.remove(id);
//    }
//}

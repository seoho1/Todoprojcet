package com.example.todoprojcet.service;

import com.example.todoprojcet.dto.TodoRequestDto;
import com.example.todoprojcet.dto.TodoResponseDto;
import com.example.todoprojcet.entity.Todo;
import com.example.todoprojcet.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoResponseDto saveTodo(TodoRequestDto dto) {

        Todo todo = new Todo(dto.getTodo(), dto.getAuthor(), dto.getPassword());

        return todoRepository.saveTodo(todo);
    }

    @Override
    public List<TodoResponseDto> findAllTodos() {

        List<TodoResponseDto> allTodos = todoRepository.findAllTodos();

        return allTodos;
    }

    @Override
    public TodoResponseDto findTodoById(Long id) {

        Todo todo = todoRepository.findTodoById(id);

        if(todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        return new TodoResponseDto(todo);
    }

    @Override
    public TodoResponseDto updateTodo(Long id, String todo) {
        Todo findBytodo = todoRepository.findTodoById(id);

        if(todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        findBytodo.update(todo);

        return new TodoResponseDto(findBytodo);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findTodoById(id);

        if(todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }
        todoRepository.deleteTodo(id);
    }
}

package com.example.todoprojcet.service;

import com.example.todoprojcet.dto.TodoRequestDto;
import com.example.todoprojcet.dto.TodoResponseDto;
import com.example.todoprojcet.entity.Todo;
import com.example.todoprojcet.repository.TodoRepository;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
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

        Todo todo = todoRepository.findTodoByIdOrElseThrow(id);

        return new TodoResponseDto(todo);
    }

    @Override
    public List<TodoResponseDto> findTodoByAuthor(String author) {

        List<TodoResponseDto> todoByAuthor = todoRepository.findTodoByAuthor(author);
        return todoByAuthor;
    }
    @Override
    public List<TodoResponseDto> findTodoUpdatedDate() {
        List<TodoResponseDto> todoByUpdatedDate = todoRepository.findTodoByUpdatedDate();
        return todoByUpdatedDate;
    }

    @Override
    public TodoResponseDto updateTodo(Long id, String todo, String password) {

        if(todo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The todo is required values " + id);
        }

        Todo Validator = todoRepository.findTodoByIdOrElseThrow(id);

        passwordValidator(id, password);

        int updatedRow = todoRepository.updateTodo(id, todo, password);

        if(updatedRow == 0 ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dose not exist id = " + id);
        }

        Todo updatedtodo = todoRepository.findTodoByIdOrElseThrow(id);
        System.out.println(updatedtodo.getUpdated_at());
        System.out.println(updatedtodo.getCreated_at());

        return new TodoResponseDto(updatedtodo);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findTodoByIdOrElseThrow(id);

        passwordValidator(id, todo.getPassword());
        todoRepository.deleteTodo(id, todo.getPassword());
    }

    public void passwordValidator(Long id, String password) {

        Todo Validator = todoRepository.findTodoByIdOrElseThrow(id);

        if(!password.equals(Validator.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is not valid.");
        }
    }
}

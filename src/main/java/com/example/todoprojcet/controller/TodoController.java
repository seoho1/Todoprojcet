package com.example.todoprojcet.controller;


import com.example.todoprojcet.dto.TodoRequestDto;
import com.example.todoprojcet.dto.TodoResponseDto;
import com.example.todoprojcet.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    //클라이언트에게 전달할 데이터를 포함
    //@Requestvbody 데이터(JSON, TEXT, XML 등)를 직접 HTTP Message Body에 담아서 사용한다.
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto dto) {

        return new ResponseEntity<>(todoService.saveTodo(dto), HttpStatus.OK);
    }

    @GetMapping
    public List<TodoResponseDto> findAllTodos() {

        return todoService.findAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findById (@PathVariable Long id){
        return new ResponseEntity<>(todoService.findTodoById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(
            @PathVariable Long id,
            @RequestBody TodoRequestDto dto
            )
    {
        return new ResponseEntity<>(todoService.updateTodo(id, dto.getTodo()),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

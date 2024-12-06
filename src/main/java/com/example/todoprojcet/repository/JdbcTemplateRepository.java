package com.example.todoprojcet.repository;

import com.example.todoprojcet.dto.TodoResponseDto;
import com.example.todoprojcet.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateRepository implements TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TodoResponseDto saveTodo(Todo todo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todo").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", todo.getTodo());
        parameters.put("author", todo.getAuthor());
        parameters.put("password", todo.getPassword());

        //저장 후 생성된 key 값 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new TodoResponseDto(key.longValue(), todo.getTodo(), todo.getAuthor(), todo.getPassword());
    }


    @Override
    public Todo findTodoById(Long id) {
        return null;
    }


    @Override
    public void deleteTodo(Long id) {

    }

    @Override
    public List<TodoResponseDto> findAllTodos() {
        return List.of();
    }


}



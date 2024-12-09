package com.example.todoprojcet.repository;

import com.example.todoprojcet.dto.TodoResponseDto;
import com.example.todoprojcet.entity.Todo;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateRepository implements TodoRepository {

    private final JdbcTemplate jdbcTemplate;


    public JdbcTemplateRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);;
    }

    @Override
    public TodoResponseDto saveTodo(Todo todo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todo").usingGeneratedKeyColumns("id").usingColumns("todo","author","password");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", todo.getTodo());
        parameters.put("author", todo.getAuthor());
        parameters.put("password", todo.getPassword());

        //저장 후 생성된 key 값 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        List<TodoResponseDto> query = jdbcTemplate.query("select * from todo where id = ?", todoRowMapper(), key.longValue());
        for (TodoResponseDto responseDto : query) {
            if(responseDto.getId() == key.longValue()){
                return responseDto;
            }
        } return null;
    }


    @Override
    public List<TodoResponseDto> findAllTodos() {
        return jdbcTemplate.query("SELECT * FROM todo", todoRowMapper());
    }

    @Override
    public Optional<Todo> findTodoById(Long id) {
        List<Todo> result = jdbcTemplate.query("select * from todo where id = ?", todoRowMapperV2(), id);
        return result.stream().findAny();
    }

    @Override
    public List<TodoResponseDto> findTodoByAuthor(String author) {
        return jdbcTemplate.query("select * from todo where author = ?", todoRowMapper(), author);
    }

    @Override
    public List<TodoResponseDto> findTodoByUpdatedDate(){
        return jdbcTemplate.query("select * from todo WHERE updated_at order by updated_at desc ", todoRowMapper());
    }

    @Override
    public int deleteTodo(Long id, String password) {
        return jdbcTemplate.update("delete from todo where id = ? and password = ?", id, password);
    }

    private RowMapper<TodoResponseDto> todoRowMapper() {

        return new RowMapper<TodoResponseDto>() {
            @Override
            public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new TodoResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("author"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class)

                );
            }
        };
    }

    private RowMapper<Todo> todoRowMapperV2() {

        return new RowMapper<Todo>() {
            @Override
            public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new Todo(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("author"),
                        rs.getString("password"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class)
                );
            }
        };
    }

    @Override
    public Todo findTodoByIdOrElseThrow(Long id) {
        List<Todo> result = jdbcTemplate.query("select * from todo where id = ?", todoRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public int updateTodo(Long id, String todo, String password) {
        return jdbcTemplate.update("update todo set todo = ? where id = ? and password = ?", todo, id, password);
    }
}



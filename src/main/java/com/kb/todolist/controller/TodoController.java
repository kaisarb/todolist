package com.kb.todolist.controller;

import com.kb.todolist.entity.TodoEntity;
import com.kb.todolist.model.Todo;
import com.kb.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public List<Todo> getAllTodos() {
        List<TodoEntity> todoEntities = todoRepository.findAll();
        List<Todo> todos = new ArrayList<>();
        for (TodoEntity todoEntity : todoEntities) {
            Todo todo = new Todo();
            todo.setId(todoEntity.getId());
            todo.setTitle(todoEntity.getTitle());
            todo.setCompleted(todoEntity.isCompleted());
            todos.add(todo);
        }
        return todos;
    }

    public Todo createTodo(@RequestBody Todo todo) {
        TodoEntity todoEntity = new TodoEntity();
        todo.setTitle(todoEntity.getTitle());
        todo.setCompleted(todo.isCompleted());
        todoEntity = todoRepository.save(todoEntity);
        todo.setId(todoEntity.getId());
        return todo;
    }

}

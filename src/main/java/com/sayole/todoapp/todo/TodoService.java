package com.sayole.todoapp.todo;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository){
        this.repository = repository;
    }

    public Todo createTodo(Todo todo){
        verifyExistTitle(todo.getTitle());
        todo.setTodoOrder(getTableSize());
        Todo savedTodo = repository.save(todo);
        return savedTodo;
    }

    private void verifyExistTitle(String title){
        Optional<Todo> todo = repository.findByTitle(title);
        if(todo.isPresent())
            throw new RuntimeException("이미 있음");
    }

    private long getTableSize(){
        return repository.count();
    }
}

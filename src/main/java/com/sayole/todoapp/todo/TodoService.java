package com.sayole.todoapp.todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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

    public Todo updateTodo(Todo todo){
        Todo foundTodo = findVerifiedTodo(todo.getId());

        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> foundTodo.setTitle(title));
        Optional.ofNullable(todo.isCompleted())
                .ifPresent(complete -> foundTodo.setCompleted(complete));
//        Optional.of(todo.getTodoOrder())
//                .ifPresent(todoOrder -> {
//                    if(!todoOrder.equals(foundTodo.getTodoOrder()))
//
//
//                });

        return repository.save(foundTodo);
    }

    public Todo findTodo(long id) { return findVerifiedTodo(id); }

    public List<Todo> findTodos(){
        return repository.findAll(Sort.by("todoOrder").descending());
    }

    public void deleteTodo(long id){
        repository.delete(findVerifiedTodo(id));
        resetTodoOrders(id);
    }

    private void verifyExistTitle(String title){
        Optional<Todo> todo = repository.findByTitle(title);
        if(todo.isPresent())
            throw new RuntimeException("이미 있음");
    }

    private Todo findVerifiedTodo(long id){
        Optional<Todo> todo = repository.findById(id);
        Todo foundMember = todo.orElseThrow(() ->new RuntimeException("없음"));
        return foundMember;
    }

    private void resetTodoOrders(long id){
        findTodos().stream().
                filter(todo -> todo.getId() > id).
                forEach(todo -> {
                    todo.setTodoOrder(todo.getTodoOrder() - 1);
                    repository.save(todo);
                });
    }

    private void changeTodoOrder(long id){

    }

    private long getTableSize(){
        return repository.count() + 1;
    }
}

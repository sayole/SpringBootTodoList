package com.sayole.todoapp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
public class TodoController {
    @Autowired
    private TodoMapper mapper;
    @Autowired
    private TodoService service;

    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoDto.Post requestBody){
        Todo todo = mapper.todoPostDtoToTodo(requestBody);
        Todo createdTodo = service.createTodo(todo);

        //추후 ResponseDto로 교체
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") @Positive long todoId,
                                    @Valid @RequestBody TodoDto.Patch requestBody){
        requestBody.setId(todoId);
        Todo todo = mapper.todoPatchDtoToTodo(requestBody);
        Todo updateTodo = service.updateTodo(todo);
        return new ResponseEntity<>(updateTodo, HttpStatus.OK);
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") @Positive long todoId){
        Todo foundTodo = service.findTodo(todoId);
        return new ResponseEntity<>(foundTodo,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodos(){
        List<Todo> todoList = service.findTodos();
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") @Positive long todoId){
        service.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.sayole.todoapp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

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
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") @Positive long todoId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodos(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable @Positive long todoId){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

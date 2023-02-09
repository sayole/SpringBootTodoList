package com.sayole.todoapp.todo;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TodoMapper {
    Todo todoPostDtoToTodo(TodoDto.Post post);
    Todo todoPatchDtoToTodo(TodoDto.Patch patch);
    //TodoResponseDto들어가야댐
}

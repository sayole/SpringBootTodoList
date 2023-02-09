package com.sayole.todoapp.mock;

import com.google.gson.Gson;
import com.sayole.todoapp.todo.Todo;
import com.sayole.todoapp.todo.TodoDto;
import com.sayole.todoapp.todo.TodoMapper;
import com.sayole.todoapp.todo.TodoService;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


//@Transactional 필요
@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private TodoService service;

    @Autowired
    private TodoMapper mapper;

    @Test
    void postTodoTest() throws Exception{
        TodoDto.Post post = new TodoDto.Post("todo 만들기");
        Todo todo = mapper.todoPostDtoToTodo(post);
        todo.setId(1L);
        todo.setTodoOrder(1);
        todo.setCompleted(false);

        given(service.createTodo(Mockito.any(Todo.class))).willReturn(todo);
        String content = gson.toJson(post);

        ResultActions actions =
                mockMvc.perform(
                        post("/")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(todo.getTitle()))
                .andExpect(jsonPath("$.todoOrder").value(todo.getTodoOrder()))
                .andExpect(jsonPath("$.completed").value(todo.isCompleted()));
    }

    void patchTodoTest() throws Exception{
        TodoDto.Patch patch = new TodoDto.Patch();
    }
}

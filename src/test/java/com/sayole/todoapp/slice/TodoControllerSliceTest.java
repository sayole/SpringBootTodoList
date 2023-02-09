package com.sayole.todoapp.slice;

import com.google.gson.Gson;
import com.sayole.todoapp.todo.Todo;
import com.sayole.todoapp.todo.TodoDto;
import com.sayole.todoapp.todo.TodoMapper;
import com.sayole.todoapp.todo.TodoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@Transactional 필요
@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerSliceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    void postTodoTest() throws Exception{
        TodoDto.Post post = new TodoDto.Post("todo 만들기");

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
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.todoOrder").value(0))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @DisplayName("patch**********************")
    @Test
    void patchTodoTest() throws Exception{
        postTodoTest();

        TodoDto.Patch patch = new TodoDto.Patch(1L,"그냥그냥", true, 0);

        String content = gson.toJson(patch);

        ResultActions actions =
                mockMvc.perform(
                        patch("/" + 1L)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(patch.getTitle()))
                .andExpect(jsonPath("$.completed").value(patch.isCompleted()));
    }
}

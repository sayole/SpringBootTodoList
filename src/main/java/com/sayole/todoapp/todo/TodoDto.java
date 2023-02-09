package com.sayole.todoapp.todo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//TODO 빌더패턴 이해 필요
public class TodoDto {
    @Getter
    @Setter
    public static class Post {
        @NotBlank(message = "입력 필요")
        private String title;
    }

    @Getter
    @Setter
    public static class Patch {
        @Setter
        private long id;

        @NotBlank
        private String title;

        @NotNull
        private boolean completed;

        private long todoOrder;
    }
}

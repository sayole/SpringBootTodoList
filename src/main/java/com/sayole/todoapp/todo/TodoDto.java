package com.sayole.todoapp.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

    //TODO 빌더패턴 이해 필요
public class TodoDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank(message = "입력 필요")
        private String title;
    }

    @Getter
    public static class Patch {
        private long id;

        @NotBlank
        private String title;

        @NotBlank
        private boolean completed;
    }
}

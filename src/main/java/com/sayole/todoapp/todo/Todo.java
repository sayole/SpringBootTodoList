package com.sayole.todoapp.todo;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column
    private long todoOrder;

    @Column(nullable = false)
    @Getter
    private boolean completed = false;
}

package com.apiwiz.Apiwiz.task.Entities;

import com.apiwiz.Apiwiz.task.Enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private Date dueDate;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn
    private User user;
}

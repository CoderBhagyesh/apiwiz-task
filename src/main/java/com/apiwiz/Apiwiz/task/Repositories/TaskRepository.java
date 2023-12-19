package com.apiwiz.Apiwiz.task.Repositories;

import com.apiwiz.Apiwiz.task.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}

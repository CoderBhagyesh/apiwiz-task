package com.apiwiz.Apiwiz.task.Repositories;

import com.apiwiz.Apiwiz.task.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}

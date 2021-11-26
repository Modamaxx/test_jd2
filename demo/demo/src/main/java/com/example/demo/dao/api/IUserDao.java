package com.example.demo.dao.api;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User,Long> {
    User findByLogin(String login);
}

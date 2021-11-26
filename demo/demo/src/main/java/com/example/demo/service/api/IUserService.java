package com.example.demo.service.api;

import com.example.demo.dto.filter.UserFilter;
import com.example.demo.model.User;

public interface IUserService extends IService<User,Long, UserFilter> {
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
}

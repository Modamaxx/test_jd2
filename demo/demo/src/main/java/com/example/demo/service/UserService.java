package com.example.demo.service;

import com.example.demo.dao.api.IUserDao;
import com.example.demo.dto.filter.UserFilter;
import com.example.demo.model.User;
import com.example.demo.dto.filter.BaseFilter;
import com.example.demo.model.api.ERole;
import com.example.demo.service.api.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService implements IUserService {

    private final IUserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {
        user.setDtCreate(LocalDateTime.now());
        user.setDtUpdate(LocalDateTime.now());
        user.setRole(ERole.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public void delete(Long id, long dtUpdate) {
        User user = userDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Пользователя по данному id не существует"));
        userDao.delete(user);
    }

    @Override
    public User get(Long id) {
        return userDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Пользователя по данному id не существует"));
    }

    @Override
    public void update(User updateUser, Long idUser, long dtUpdate) {
        User user = get(idUser);

        updateUser.setDtCreate(user.getDtCreate());
        updateUser.setDtUpdate(LocalDateTime.now());
        updateUser.setId(idUser);
        updateUser.setRole(user.getRole());
        updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(updateUser);
    }

    @Override
    public Page<User> getAll(UserFilter filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());
        return userDao.findAll(pageable);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        throw new IllegalArgumentException("Данные введены неверно, проверьте ваш пароль или логин");
    }
}

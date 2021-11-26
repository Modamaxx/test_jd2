package com.example.demo.service.validator;

import com.example.demo.dto.filter.BaseFilter;
import com.example.demo.dto.filter.UserFilter;
import com.example.demo.model.User;
import com.example.demo.model.Weight;
import com.example.demo.service.api.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Aspect
@Component
public class UserValidatorService {
    private final IUserService userService;

    public UserValidatorService(IUserService userService) {
        this.userService = userService;
    }

    @Before("execution(* com.example.demo.service.UserService.update(..))")
    public void update(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        User updateUser = (User) args[0];
        Long idUser = (Long) args[1];
        long dtUpdate = (long) args[2];

        User user = userService.get(idUser);

        if (dtUpdate <= 0) {
            throw new IllegalArgumentException("время изменения неверного формата");
        }
        long dtBd = user.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (dtBd != dtUpdate) {
            throw new IllegalArgumentException("Данные в базе изменились обновите страницу");
        }

        validBasicParameters(updateUser);

    }

    @Before("execution(* com.example.demo.service.UserService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        Long idUser = (Long) args[0];
        long dtUpdate = (long) args[1];

        User user = userService.get(idUser);

        if (dtUpdate <= 0) {
            throw new IllegalArgumentException("время изменения неверного формата");
        }
        long dtBd = user.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (dtBd != dtUpdate) {
            throw new IllegalArgumentException("Данные в базе изменились обновите страницу");
        }

    }

    @Before("execution(* com.example.demo.service.UserService.getAll(..))")
    public void getAll(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        UserFilter userFilter = (UserFilter) args[0];

        if (userFilter.getPage()<0) {
            throw new IllegalArgumentException("Проверьте ваши параметры");
        }
        if (userFilter.getSize()<0) {
            throw new IllegalArgumentException("Проверьте ваши параметры");
        }

    }

    @Before("execution(* com.example.demo.service.UserService.save(..))")
    public void save(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        User user = (User) args[0];
        validBasicParameters(user);
        User userBd =userService.findByLogin(user.getLogin());

        if(userBd!=null){
            throw new IllegalArgumentException("Данный пользователь уже существует" );
        }
    }

    @Before("execution(* com.example.demo.service.UserService.findByLoginAndPassword(..))")
    public void findByLoginAndPassword(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String login = (String) args[0];
        String password = (String) args[1];

        if(login==null||login.equals("")){
            throw new IllegalArgumentException("Не указан логин");
        }

        if(password==null||password.equals("")){
            throw new IllegalArgumentException("Не указан пароль");
        }
    }

    public void validBasicParameters(User user){
        if (user.getPassword() == null||user.getPassword().equals("")) {
            throw new IllegalArgumentException("Не указан пароль");

        }
        if (user.getLogin() == null||user.getLogin().equals("")) {
            throw new IllegalArgumentException("Не указан логин");

        }
    }

}

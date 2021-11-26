package com.example.demo.service.audit;

import com.example.demo.model.Audit;
import com.example.demo.model.User;
import com.example.demo.model.api.EssenceName;
import com.example.demo.service.UserService;
import com.example.demo.service.api.IAuditService;
import com.example.demo.service.api.IUserService;
import com.example.security.UserHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Aspect
@Service
public class UserAuditService {

    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public UserAuditService(IAuditService auditService, UserHolder userHolder, IUserService userService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @AfterReturning("execution(* com.example.demo.service.UserService.save(..))")
    public void save(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            User userModified  = (User) args[0];
            Audit audit = new Audit();

            audit.setDtCreate(userModified.getDtUpdate());
            audit.setDescription("Пользователь зарегистрерировался");
            audit.setEssenceName(EssenceName.USER);
            audit.setEssenceId(userModified.getId());


            audit.setUserId(userModified.getId());


            auditService.save(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка записи пользователя в аудите");
        }
    }

    @AfterReturning("execution(* com.example.demo.service.UserService.update(..))")
    public void update(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            User userModified  = (User) args[0];
            Long id=(Long) args[1];
            Audit audit = new Audit();

            audit.setDtCreate(userModified.getDtUpdate());
            audit.setDescription("У Пользователь внесены изменения");
            audit.setEssenceName(EssenceName.USER);
            audit.setEssenceId(id);

            String login = userHolder.getAuthentication().getName();
            User user=userService.findByLogin(login);
            audit.setUserId(user.getId());

            auditService.save(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка изменение пользователя в аудите");
        }
    }

    @AfterReturning("execution(* com.example.demo.service.UserService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long idUser=(Long) args[0];
            Audit audit = new Audit();

            audit.setDtCreate(LocalDateTime.now());
            audit.setDescription("Пользователь удален");
            audit.setEssenceName(EssenceName.USER);
            audit.setEssenceId(idUser);

            String login = userHolder.getAuthentication().getName();
            User user=userService.findByLogin(login);
            audit.setUserId(user.getId());

            auditService.save(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка удаление пользователя в аудите");
        }
    }
}

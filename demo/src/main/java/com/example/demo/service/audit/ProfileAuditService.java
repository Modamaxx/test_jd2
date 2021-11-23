package com.example.demo.service.audit;

import com.example.demo.model.Audit;
import com.example.demo.model.Profile;
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
public class ProfileAuditService {

    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public ProfileAuditService(IAuditService auditService, UserHolder userHolder, IUserService userService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @AfterReturning("execution(* com.example.demo.service.ProfileService.save(..))")
    public void save(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Profile profileModified  = (Profile) args[0];

            Audit audit = new Audit();

            audit.setDtCreate(profileModified.getDtUpdate());
            audit.setDescription("Пользователь создал профиль");
            audit.setEssenceName(EssenceName.PROFILE);
            Long idProfile =profileModified.getId();
            audit.setEssenceId(idProfile);

            String login = userHolder.getAuthentication().getName();
            User user=userService.findByLogin(login);
            audit.setUser(user);


            auditService.save(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка создания профилья в аудите");
        }
    }

    @AfterReturning("execution(* com.example.demo.service.ProfileService.update(..))")
    public void update(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Profile profileModified  = (Profile) args[0];
            Long idProfile=(Long) args[1];
            Audit audit = new Audit();

            audit.setDtCreate(profileModified.getDtUpdate());
            audit.setDescription("В профилье внесены изменения");
            audit.setEssenceName(EssenceName.PROFILE);
            audit.setEssenceId(idProfile);

            String login = userHolder.getAuthentication().getName();
            User user=userService.findByLogin(login);
            audit.setUser(user);

            auditService.update(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка изменение профиля в аудите");
        }
    }
    @AfterReturning("execution(* com.example.demo.service.ProfileService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long idProfile = (Long) args[0];
            long dtUpdate = (long) args[1];

            Audit audit = new Audit();

            audit.setDtCreate(LocalDateTime.now());
            audit.setDescription("Профиль удален");
            audit.setEssenceName(EssenceName.PROFILE);
            audit.setEssenceId(idProfile);

            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);

            auditService.save(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка удаление профиля в аудите");
        }
    }
}

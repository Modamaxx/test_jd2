package com.example.demo.service.audit;

import com.example.demo.model.Active;
import com.example.demo.model.Audit;
import com.example.demo.model.User;
import com.example.demo.model.Weight;
import com.example.demo.model.api.EssenceName;
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
public class ActiveAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public ActiveAuditService(IAuditService auditService, UserHolder userHolder, IUserService userService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @AfterReturning("execution(* com.example.demo.service.ActiveService.save(..))")
    public void save(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Active active = (Active) args[0];
            Audit audit = new Audit();

            audit.setDtCreate(active.getDtUpdate());
            audit.setDescription("активность сохранена");
            audit.setEssenceName(EssenceName.ACTIVE);
            Long id = active.getId();
            audit.setEssenceId(id);

            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUserId(user.getId());

            auditService.save(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка создание активности в аудите");
        }
    }

    @AfterReturning("execution(* com.example.demo.service.ActiveService.update(..))")
    public void update(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Active active = (Active) args[0];
            Long idActive = (Long) args[1];
            Audit audit = new Audit();

            audit.setDtCreate(active.getDtUpdate());
            audit.setDescription("Активность изменена");
            audit.setEssenceName(EssenceName.ACTIVE);
            audit.setEssenceId(idActive);

            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUserId(user.getId());

            auditService.update(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка изменение активности в аудите");
        }
    }

    @AfterReturning("execution(* com.example.demo.service.ActiveService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long idActive = (Long) args[0];

            Audit audit = new Audit();

            audit.setDtCreate(LocalDateTime.now());
            audit.setDescription("Активность удалена");
            audit.setEssenceName(EssenceName.ACTIVE);
            audit.setEssenceId(idActive);

            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUserId(user.getId());

            auditService.save(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка удаление активности в аудите");
        }
    }
}

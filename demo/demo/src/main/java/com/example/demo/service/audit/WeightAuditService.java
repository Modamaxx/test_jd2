package com.example.demo.service.audit;

import com.example.demo.model.Audit;
import com.example.demo.model.Journal;
import com.example.demo.model.User;
import com.example.demo.model.Weight;
import com.example.demo.model.api.EssenceName;
import com.example.demo.service.api.IAuditService;
import com.example.demo.service.api.IProductService;
import com.example.demo.service.api.IProfileService;
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
public class WeightAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public WeightAuditService(IAuditService auditService, UserHolder userHolder, IUserService userService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @AfterReturning("execution(* com.example.demo.service.WeightService.save(..))")
    public void save(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Weight weight = (Weight) args[0];
            Audit audit = new Audit();

            audit.setDtCreate(weight.getDtUpdate());
            audit.setDescription("Вес сохранен");
            audit.setEssenceName(EssenceName.WEIGHT);
            Long id =weight.getId();
            audit.setEssenceId(id);

            String login = userHolder.getAuthentication().getName();
            User user=userService.findByLogin(login);
            audit.setUserId(user.getId());

            auditService.save(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка создание веса в аудите");
        }
    }

    @AfterReturning("execution(* com.example.demo.service.WeightService.update(..))")
    public void update(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Weight weight = (Weight) args[0];
            Long idWeight = (Long) args[1];
            Audit audit = new Audit();

            audit.setDtCreate(weight.getDtUpdate());
            audit.setDescription("Вес изменен");
            audit.setEssenceName(EssenceName.WEIGHT);
            audit.setEssenceId(idWeight);

            String login = userHolder.getAuthentication().getName();
            User user=userService.findByLogin(login);
            audit.setUserId(user.getId());

            auditService.update(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка изменение веса в аудите");
        }
    }
    @AfterReturning("execution(* com.example.demo.service.WeightService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long idWeight = (Long) args[0];


            Audit audit = new Audit();

            audit.setDtCreate(LocalDateTime.now());
            audit.setDescription("Запись веса удалена");
            audit.setEssenceName(EssenceName.WEIGHT);
            audit.setEssenceId(idWeight);

            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUserId(user.getId());

            auditService.save(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка удаление записи веса в аудите");
        }
    }
}

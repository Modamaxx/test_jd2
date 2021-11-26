package com.example.demo.service.audit;

import com.example.demo.model.Audit;
import com.example.demo.model.Journal;
import com.example.demo.model.Product;
import com.example.demo.model.User;
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
public class JournalsFoodAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public JournalsFoodAuditService(IAuditService auditService, UserHolder userHolder, IUserService userService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @AfterReturning("execution(* com.example.demo.service.JournalsFoodService.save(..))")
    public void save(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Journal journal = (Journal) args[0];
            Audit audit = new Audit();

            audit.setDtCreate(journal.getDtUpdate());
            audit.setDescription("Прием пищи в журнал сохранен");
            audit.setEssenceName(EssenceName.JOURNAL);
            Long id =journal.getId();
            audit.setEssenceId(id);

            String login = userHolder.getAuthentication().getName();
            User user=userService.findByLogin(login);
            audit.setUserId(user.getId());

            auditService.save(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка создание записи в журнал в аудите");
        }
    }

    @AfterReturning("execution(* com.example.demo.service.JournalsFoodService.update(..))")
    public void update(JoinPoint joinPoint) {
        try {

            Object[] args = joinPoint.getArgs();
            Journal journal = (Journal) args[0];
            Long idFood  = (Long) args[1];


            Audit audit = new Audit();

            audit.setDtCreate(journal.getDtUpdate());
            audit.setDescription("Журнал изменен");
            audit.setEssenceName(EssenceName.JOURNAL);
            audit.setEssenceId(idFood);

            String login = userHolder.getAuthentication().getName();
            User user=userService.findByLogin(login);
            audit.setUserId(user.getId());

            auditService.save(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка изменение в журнале в аудите");
        }
    }

    @AfterReturning("execution(* com.example.demo.service.JournalsFoodService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long idFood = (Long) args[0];

            Audit audit = new Audit();

            audit.setDtCreate(LocalDateTime.now());
            audit.setDescription("Запись в журнале удалена");
            audit.setEssenceName(EssenceName.PRODUCT);
            audit.setEssenceId(idFood);

            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUserId(user.getId());

            auditService.save(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка удаление записи журнала в аудите");
        }
    }


}

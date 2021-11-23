package com.example.demo.service.audit;

import com.example.demo.model.Audit;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.model.api.EssenceName;
import com.example.demo.service.RecipeService;
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
public class RecipeAuditService {

    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public RecipeAuditService(IAuditService auditService, UserHolder userHolder, IUserService userService, RecipeService recipeService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @AfterReturning("execution(* com.example.demo.service.RecipeService.save(..))")
    public void save(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Recipe recipeModified = (Recipe) args[0];

            Audit audit = new Audit();

            audit.setDtCreate(recipeModified.getDtUpdate());
            audit.setDescription("Пользователь создал блюдо");
            audit.setEssenceName(EssenceName.RECIPE);
            Long idRecipe = recipeModified.getId();
            audit.setEssenceId(idRecipe);

            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);


            auditService.save(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка создания блюда в аудите");
        }
    }

    @AfterReturning("execution(* com.example.demo.service.RecipeService.update(..))")
    public void update(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Recipe recipeModified = (Recipe) args[0];
            Long idRecipe = (Long) args[1];
            Audit audit = new Audit();

            audit.setDtCreate(recipeModified.getDtUpdate());
            audit.setDescription("В блюдо внесены изменения");
            audit.setEssenceName(EssenceName.RECIPE);
            audit.setEssenceId(idRecipe);

            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);

            auditService.update(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка изменение блюда в аудите");
        }
    }

    @AfterReturning("execution(* com.example.demo.service.RecipeService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long idRecipe = (Long) args[0];

            Audit audit = new Audit();

            audit.setDtCreate(LocalDateTime.now());
            audit.setDescription("Блюдо удалено");
            audit.setEssenceName(EssenceName.RECIPE);
            audit.setEssenceId(idRecipe);

            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);

            auditService.save(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка удаление записи блюда в аудите");
        }
    }
}

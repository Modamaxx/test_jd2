package com.example.demo.service.validator;

import com.example.demo.dto.filter.ActiveFilter;
import com.example.demo.dto.filter.BaseFilter;
import com.example.demo.dto.filter.ProductFilter;
import com.example.demo.model.Active;
import com.example.demo.model.Journal;
import com.example.demo.service.api.IActiveService;
import com.example.demo.service.api.IJournalsFoodService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Aspect
@Component
public class ActiveValidatorService {
    private final IActiveService activeService;

    public ActiveValidatorService(IActiveService activeService) {
        this.activeService = activeService;
    }

    @Before("execution(* com.example.demo.service.ActiveService.getAll(..))")
    public void getAll(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        ActiveFilter activeFilter = (ActiveFilter) args[0];

        if (activeFilter.getPage() == null || activeFilter.getSize() == null) {
            throw new IllegalArgumentException("Не указаны обязательные параметры для пагинации");
        }

    }

    @Before("execution(* com.example.demo.service.ActiveService.save(..))")
    public void save(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Active active = (Active) args[0];
        validBasicParameters(active);

    }

    @Before("execution(* com.example.demo.service.ActiveService.update(..))")
    public void update(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Active activeUpdate = (Active) args[0];
        Long idActive = (Long) args[1];
        Long idProfile = (Long) args[2];
        long dtUpdate = (long) args[3];

        Active active = activeService.get(idActive, idProfile);

        if (dtUpdate <= 0) {
            throw new IllegalArgumentException("время изменения неверного формата");
        }
        long dtBd = active.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (dtBd != dtUpdate) {
            throw new IllegalArgumentException("Данные в базе изменились обновите страницу");
        }

        validBasicParameters(activeUpdate);

    }

    @Before("execution(* com.example.demo.service.ActiveService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Long idActive = (Long) args[0];
        Long idProfile = (Long) args[1];
        long dtUpdate = (long) args[2];

        if (dtUpdate <= 0) {
            throw new IllegalArgumentException("время изменения неверного формата");
        }

        Active active = activeService.get(idActive, idProfile);
        long dtBd = active.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (dtBd != dtUpdate) {
            throw new IllegalArgumentException("Данные в базе изменились обновите страницу");
        }

    }

    public void validBasicParameters(Active active){
        if (active.getName() == null) {
            throw new IllegalArgumentException("Не указано название вашей активности");

        }
        if (active.getCalories() <= 0) {
            throw new IllegalArgumentException("Активность указано неверно");
        }
    }
}

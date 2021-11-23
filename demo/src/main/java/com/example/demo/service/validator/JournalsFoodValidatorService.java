package com.example.demo.service.validator;

import com.example.demo.dto.filter.BaseFilter;
import com.example.demo.dto.filter.JournalFilter;
import com.example.demo.model.Journal;
import com.example.demo.model.Product;
import com.example.demo.model.api.EEating;
import com.example.demo.service.api.IJournalsFoodService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Aspect
@Component
public class JournalsFoodValidatorService {
    private final IJournalsFoodService journalsFoodService;

    public JournalsFoodValidatorService(IJournalsFoodService journalsFoodService) {
        this.journalsFoodService = journalsFoodService;
    }

    @Before("execution(* com.example.demo.service.JournalsFoodService.getAll(..))")
    public void getAll(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Long idProfile = (Long) args[0];
        BaseFilter journalFilter= (BaseFilter) args[1];
        if(journalFilter.getPage()==null||journalFilter.getSize()==null){
            throw new IllegalArgumentException("Не указаны обязательные параметры для пагинации");
        }
    }

    @Before("execution(* com.example.demo.service.JournalsFoodService.save(..))")
    public void save(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Journal journal = (Journal) args[0];
        if(journal.getRecipe()==null&&journal.getProduct()==null){
            throw new IllegalArgumentException("Не указано что съел пользователь");
        }

        if (journal.getTypeEating()==null) {
            throw new IllegalArgumentException("Неизвестный прием пищи");
        }
        if (journal.getMass() <= 0) {
            throw new IllegalArgumentException("Масса продукта введена неверено");
        }
    }

    @Before("execution(* com.example.demo.service.JournalsFoodService.update(..))")
    public void update(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Journal journalUpdate = (Journal) args[0];
        Long idFood = (Long) args[1];
        Long idProfile = (Long) args[2];
        long dtUpdate = (long) args[3];

        Journal journal = journalsFoodService.get(idProfile, idFood);

        if (dtUpdate <= 0) {
            throw new IllegalArgumentException("время изменения неверного формата");
        }

        long dtBd = journal.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (dtBd != dtUpdate) {
            throw new IllegalArgumentException("Данные в базе изменились обновите страницу");
        }

        if (journalUpdate.getTypeEating()==null) {
            throw new IllegalArgumentException("Неизвестный прием пищи");
        }
        if (journalUpdate.getMass() <= 0) {
            throw new IllegalArgumentException("Масса продукта введена неверено");
        }
    }

    @Before("execution(* com.example.demo.service.JournalsFoodService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Long idFood = (Long) args[0];
        Long idProfile = (Long) args[1];
        long dtUpdate = (long) args[2];

        if (dtUpdate <= 0) {
            throw new IllegalArgumentException("время изменения неверного формата");
        }

        Journal journal = journalsFoodService.get(idProfile, idFood);
        long dtBd = journal.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (dtBd != dtUpdate) {
            throw new IllegalArgumentException("Данные в базе изменились обновите страницу");
        }

    }
}

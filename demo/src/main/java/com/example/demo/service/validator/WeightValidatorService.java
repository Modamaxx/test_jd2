package com.example.demo.service.validator;

import com.example.demo.dto.filter.ActiveFilter;
import com.example.demo.model.Active;
import com.example.demo.model.Weight;
import com.example.demo.service.api.IActiveService;
import com.example.demo.service.api.IWeightService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Aspect
@Component
public class WeightValidatorService {
    private final IWeightService weightService;

    public WeightValidatorService(IWeightService weightService) {
        this.weightService = weightService;
    }

    @Before("execution(* com.example.demo.service.WeightService.getAll(..))")
    public void getAll(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        ActiveFilter activeFilter = (ActiveFilter) args[0];

        if (activeFilter.getPage() == null || activeFilter.getSize() == null) {
            throw new IllegalArgumentException("Не указаны обязательные параметры для пагинации");
        }

    }

    @Before("execution(* com.example.demo.service.WeightService.save(..))")
    public void save(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Weight weight = (Weight) args[0];


        if (weight.getWeight() <= 0) {
            throw new IllegalArgumentException("Вес указан неверно");

        }
    }

    @Before("execution(* com.example.demo.service.WeightService.update(..))")
    public void update(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Weight weightUpdate = (Weight) args[0];
        Long idWeight = (Long) args[1];
        Long idProfile = (Long) args[2];
        long dtUpdate = (long) args[3];

        Weight weight = weightService.get(idWeight, idProfile);

        if (dtUpdate <= 0) {
            throw new IllegalArgumentException("время изменения неверного формата");
        }
        long dtBd = weight.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (dtBd != dtUpdate) {
            throw new IllegalArgumentException("Данные в базе изменились обновите страницу");
        }

        if (weightUpdate.getWeight() <= 0) {
            throw new IllegalArgumentException("Вес указан неверно");

        }

    }

    @Before("execution(* com.example.demo.service.WeightService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Long idWeight = (Long) args[0];
        Long idProfile = (Long) args[1];
        long dtUpdate = (long) args[2];

        if (dtUpdate <= 0) {
            throw new IllegalArgumentException("время изменения неверного формата");
        }

        Weight weight = weightService.get(idWeight, idProfile);
        long dtBd = weight.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (dtBd != dtUpdate) {
            throw new IllegalArgumentException("Данные в базе изменились обновите страницу");
        }

    }
}

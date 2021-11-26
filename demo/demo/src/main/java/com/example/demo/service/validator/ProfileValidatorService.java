package com.example.demo.service.validator;

import com.example.demo.dto.filter.BaseFilter;
import com.example.demo.dto.filter.ProfileFilter;
import com.example.demo.model.Journal;
import com.example.demo.model.Product;
import com.example.demo.model.Profile;
import com.example.demo.model.api.EEating;
import com.example.demo.service.api.IJournalsFoodService;
import com.example.demo.service.api.IProfileService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Aspect
@Component
public class ProfileValidatorService {
    private final IProfileService profileService;

    public ProfileValidatorService(IProfileService profileService) {
        this.profileService = profileService;
    }

    @Before("execution(* com.example.demo.service.ProfileService.getAll(..))")
    public void getAll(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        ProfileFilter profileFilter = (ProfileFilter) args[0];
        if (profileFilter.getPage() == null || profileFilter.getSize() == null) {
            throw new IllegalArgumentException("Не указаны обязательные параметры для пагинации");
        }
    }

    @Before("execution(* com.example.demo.service.ProfileService.save(..))")
    public void save(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Profile profile = (Profile) args[0];
        validBasicParameters(profile);


    }

    @Before("execution(* com.example.demo.service.ProfileService.update(..))")
    public void update(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Profile updateProfile = (Profile) args[0];
        Long idProfile = (Long) args[1];
        long dtUpdate = (long) args[2];

        Profile profile = profileService.get(idProfile);
        long dtBd = profile.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (dtUpdate <= 0) {
            throw new IllegalArgumentException("время изменения неверного формата");
        }

        if (dtBd != dtUpdate) {
            throw new IllegalArgumentException("Данные в базе изменились обновите страницу");
        }

        validBasicParameters(updateProfile);
    }

    @Before("execution(* com.example.demo.service.ProfileService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Long idProfile = (Long) args[0];
        long dtUpdate = (long) args[1];

        if (dtUpdate <= 0) {
            throw new IllegalArgumentException("время изменения неверного формата");
        }

        Profile profile = profileService.get(idProfile);
        long dtBd = profile.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (dtBd != dtUpdate) {
            throw new IllegalArgumentException("Данные в базе изменились обновите страницу");
        }

    }

    public void validBasicParameters(Profile profile) {
        if (profile.getBirthday() == null || profile.getBirthday().equals("")) {
            throw new IllegalArgumentException("Не указано дата рождения");
        }

        if (profile.getGender() == null) {
            throw new IllegalArgumentException("Не указан гендер");
        }

        if (profile.getGoal() == null) {
            throw new IllegalArgumentException("Не указана ваша желаемая форма тела");
        }

        if (profile.getGoalWeight() <= 0) {
            throw new IllegalArgumentException("Целовой вес указан неверно");
        }

        if (profile.getHeight() <= 0) {
            throw new IllegalArgumentException("рост указан неверно");
        }
        if (profile.getLifestyle() == null) {
            throw new IllegalArgumentException("Не указан образ жизни");
        }
        if (profile.getWeightActual() <= 0) {
            throw new IllegalArgumentException("Текущий вес указан неверно");
        }
    }
}

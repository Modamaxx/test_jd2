package com.example.demo.service;

import com.example.demo.dto.models.ProfileDto;
import com.example.demo.model.Profile;
import com.example.demo.model.Weight;
import com.example.demo.model.api.EGender;
import com.example.demo.model.api.EGoal;
import com.example.demo.model.api.ELifestyle;
import com.example.demo.service.api.ICalculationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class CalculationService implements ICalculationService {

    public ProfileDto norma(Profile profile) {
        ProfileDto profileDto = new ProfileDto();

        double weight = profile.getWeightActual();
        double height = profile.getHeight();
        int age = LocalDate.now().getYear() - profile.getBirthday().getYear();
        if (profile.getBirthday().getDayOfYear() >= LocalDate.now().getDayOfYear()) {
            age++;
        }

        ELifestyle lifestyle = ELifestyle.valueOfIgnoreCase(profile.getLifestyle().name());
        double active = lifestyle.getV();

        EGoal goal = EGoal.valueOfIgnoreCase(profile.getGoal().name());
        double goalWeight = goal.getI();

        String gender = profile.getGender().name();
        double calories = ((((10 * weight) + (6.25 * height)) - (5 * age)));
        if (gender.equals("MAN")) {
            calories = (calories + 5) * active * goalWeight;
        } else {
            calories = (calories - 161) * active * goalWeight;
        }

        double protein = (calories * 0.3) / 4;
        double fats = (calories * 0.3) / 9;
        double carbohydrates = (calories * 0.4) / 4;

        profileDto.setNormCalories(calories);
        profileDto.setNormProtein(protein);
        profileDto.setNormFats(fats);
        profileDto.setNormCarbohydrates(carbohydrates);

        profileDto.setBirthday(profile.getBirthday());
        profileDto.setDtCreate(profile.getDtCreate());
        profileDto.setGender(profile.getGender());
        profileDto.setGoal(profile.getGoal());
        profileDto.setDtUpdate(profile.getDtUpdate());
        profileDto.setHeight(profile.getHeight());
        profileDto.setLifestyle(profile.getLifestyle());
        profileDto.setUser(profile.getUser());
        profileDto.setWeightActual(profile.getWeightActual());
        profileDto.setId(profile.getId());
        profileDto.setGoalWeight(profile.getGoalWeight());

        return profileDto;
    }
}

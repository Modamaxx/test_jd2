package com.example.demo.service;

import com.example.demo.dto.models.ProfileDto;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Journal;
import com.example.demo.model.Profile;
import com.example.demo.model.api.EGoal;
import com.example.demo.model.api.ELifestyle;
import com.example.demo.service.api.ICalculationService;
import com.example.demo.service.api.IJournalsFoodService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class CalculationService implements ICalculationService {
    private final IJournalsFoodService journalsFoodService;

    public CalculationService(IJournalsFoodService journalsFoodService) {
        this.journalsFoodService = journalsFoodService;
    }

    public ProfileDto normaUser(Profile profile) {
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

        profileDto.setNormCalories((int)calories);
        profileDto.setNormProtein((int)protein);
        profileDto.setNormFats((int)fats);
        profileDto.setNormCarbohydrates((int)carbohydrates);
        return profileDto;
    }

    @Override
    public ProfileDto normaEat(ProfileDto profileDto,Long idProfile) {
        long day = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        List<Journal> journals = journalsFoodService.getOneDay(idProfile, day);
        double calories = 0;
        double protein = 0;
        double fats = 0;
        double carbohydrates = 0;
        double part = 0;
        for (Journal j : journals) {
            if (j.getRecipe() != null) {
                for (Ingredient i : j.getRecipe().getIngredient()) {
                    part=(i.getMass()*100)/i.getProduct().getMass();
                    calories = calories + (i.getProduct().getCalories() * part);
                    protein = protein + (i.getProduct().getProteins() * part);
                    fats = fats + (i.getProduct().getFats() * part);
                    carbohydrates = carbohydrates + (i.getProduct().getCarbohydrates() * part);
                }
            }
            if (j.getProduct() != null) {
                part = (j.getMass() * 100) / j.getProduct().getMass();
                calories = calories + (j.getProduct().getCalories() * part);
                protein = protein + (j.getProduct().getProteins() * part);
                fats = fats + (j.getProduct().getFats() * part);
                carbohydrates = carbohydrates + (j.getProduct().getCarbohydrates() * part);

            }

        }
        profileDto.setActualCalories((int)calories);
        profileDto.setActualProtein((int)protein);
        profileDto.setActualCarbohydrates((int)carbohydrates);
        profileDto.setActualFats((int)fats);

        return profileDto;
    }
}

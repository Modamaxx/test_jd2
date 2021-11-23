package com.example.demo.dto.models;

import com.example.demo.model.Profile;

public class ProfileDto extends Profile {

    private long microseconds;
    private double normCalories;
    private double normFats;
    private double normCarbohydrates;
    private double normProtein;


    public double getNormCalories() {
        return normCalories;
    }

    public void setNormCalories(double normCalories) {
        this.normCalories = normCalories;
    }

    public double getNormFats() {
        return normFats;
    }

    public void setNormFats(double normFats) {
        this.normFats = normFats;
    }

    public double getNormCarbohydrates() {
        return normCarbohydrates;
    }

    public void setNormCarbohydrates(double normCarbohydrates) {
        this.normCarbohydrates = normCarbohydrates;
    }

    public double getNormProtein() {
        return normProtein;
    }

    public void setNormProtein(double normProtein) {
        this.normProtein = normProtein;
    }

    public long getMicroseconds() {
        return microseconds;
    }

    public void setMicroseconds(long microseconds) {
        this.microseconds = microseconds;
    }
}

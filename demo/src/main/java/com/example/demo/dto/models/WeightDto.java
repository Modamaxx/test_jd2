package com.example.demo.dto.models;

import com.example.demo.model.Weight;

public class WeightDto {
    private Weight weight;
    private long microseconds;

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public long getMicroseconds() {
        return microseconds;
    }

    public void setMicroseconds(long microseconds) {
        this.microseconds = microseconds;
    }
}

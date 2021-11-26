package com.example.demo.dto.models;

import com.example.demo.model.Active;

public class ActiveDto {
    private Active active;
    private long microseconds;

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }

    public long getMicroseconds() {
        return microseconds;
    }

    public void setMicroseconds(long microseconds) {
        this.microseconds = microseconds;
    }
}

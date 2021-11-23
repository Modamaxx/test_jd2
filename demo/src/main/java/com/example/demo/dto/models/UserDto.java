package com.example.demo.dto.models;

import com.example.demo.model.User;

public class UserDto {
    private User user;
    private long microseconds;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getMicroseconds() {
        return microseconds;
    }

    public void setMicroseconds(long microseconds) {
        this.microseconds = microseconds;
    }
}

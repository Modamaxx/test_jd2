package com.example.demo.model;

import com.example.demo.model.api.EGender;
import com.example.demo.model.api.EGoal;
import com.example.demo.model.api.ELifestyle;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "birthday")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    @Column(name = "gender")
    private EGender gender;

    @Column(name = "lifestyle")
    private ELifestyle lifestyle;

    @Column(name = "weightActual")
    private double weightActual;

    @Column(name = "goal")
    private EGoal goal;

    @Column(name = "createTime")
    private LocalDateTime dtCreate;

    @Column(name = "updateTime")
    private LocalDateTime dtUpdate;

    @Column(name ="goalWeight" )
    private double goalWeight;

    @Column(name = "height")
    private double height;

    @OneToOne
    private User user;



    public double getWeightActual() {
        return weightActual;
    }

    public void setWeightActual(double weightActual) {
        this.weightActual = weightActual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public ELifestyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(ELifestyle lifestyle) {
        this.lifestyle = lifestyle;
    }

    public EGoal getGoal() {
        return goal;
    }

    public void setGoal(EGoal goal) {
        this.goal = goal;
    }

    public double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }
}

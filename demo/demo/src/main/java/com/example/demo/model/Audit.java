package com.example.demo.model;

import com.example.demo.model.api.EssenceName;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(name = "dtCreate")
    private LocalDateTime dtCreate;

    @Column(name = "description")
    private String description;

    @Column(name = "essenceName")
    private EssenceName essenceName;

    @Column(name = "essenceId")
    private Long essenceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EssenceName getEssenceName() {
        return essenceName;
    }

    public void setEssenceName(EssenceName essenceName) {
        this.essenceName = essenceName;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public Long getEssenceId() {
        return essenceId;
    }

    public void setEssenceId(Long essenceId) {
        this.essenceId = essenceId;
    }
}

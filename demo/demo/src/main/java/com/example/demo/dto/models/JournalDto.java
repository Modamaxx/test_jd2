package com.example.demo.dto.models;

import com.example.demo.dao.api.IJournalsFoodDao;
import com.example.demo.dao.api.IProfileDao;
import com.example.demo.model.Journal;
import com.example.demo.service.JournalsFoodService;
import com.example.demo.service.api.IProductService;
import com.example.demo.service.api.IProfileService;
import com.example.demo.service.api.IRecipeService;

public class JournalDto {

    private Journal journal;
    private long microseconds;

    public long getMicroseconds() {
        return microseconds;
    }

    public void setMicroseconds(long microseconds) {
        this.microseconds = microseconds;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }
}



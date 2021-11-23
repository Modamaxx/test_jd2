package com.example.demo.service.api;

import com.example.demo.model.Journal;
import com.example.demo.dto.filter.BaseFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IJournalsFoodService {
    void save(Journal journal, Long idProfile);

    Journal get(Long idProfile, Long idFood);

    Page<Journal> getAll(Long idProfile, BaseFilter baseFilter);

    List<Journal> getOneDay(Long idProfile, long day);

    void update(Journal updateJournal, Long idFood, Long idProfile, long dtUpdate);

    void delete(Long idFood, Long idProfile, long dtUpdate);
}

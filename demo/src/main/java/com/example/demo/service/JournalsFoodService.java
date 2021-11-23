package com.example.demo.service;

import com.example.demo.dao.api.IJournalsFoodDao;
import com.example.demo.dao.api.IProfileDao;
import com.example.demo.model.Journal;
import com.example.demo.dto.filter.BaseFilter;
import com.example.demo.model.Product;
import com.example.demo.model.Profile;
import com.example.demo.model.Recipe;
import com.example.demo.service.api.IJournalsFoodService;
import com.example.demo.service.api.IProductService;
import com.example.demo.service.api.IProfileService;
import com.example.demo.service.api.IRecipeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class JournalsFoodService implements IJournalsFoodService {

    private final IJournalsFoodDao journalDao;
    private final IProfileService profileService;
    private final IRecipeService recipeService;
    private final IProductService productService;

    public JournalsFoodService(IJournalsFoodDao journalDao, IProfileDao profileDao, IProfileService profileService, IRecipeService recipeService, IProductService productService) {
        this.journalDao = journalDao;
        this.profileService = profileService;
        this.recipeService = recipeService;
        this.productService = productService;
    }

    @Override
    public void save(Journal journal, Long idProfile) {
        Profile profile = profileService.get(idProfile);
        journal.setProfile(profile);
        LocalDateTime time = LocalDateTime.now();

        journal.setDtCreate(time);
        journal.setDtUpdate(time);

        if (journal.getProduct() != null) {
            Product product = productService.get(journal.getProduct().getId());
            journal.setProduct(product);
            journalDao.save(journal);
        } else {
            Recipe recipe = recipeService.get(journal.getRecipe().getId());
            journal.setRecipe(recipe);
            journal.setRecipe(recipe);
        }
        journalDao.save(journal);

    }

    @Override
    public Journal get(Long idProfile, Long idFood) {
        Journal journal = journalDao.findByProfileIdAndId(idProfile, idFood);
        if (journal == null) {
            throw new IllegalArgumentException("Не существует записи по заданым id");
        }
        return journal;
    }

    @Override
    public Page<Journal> getAll(Long idProfile, BaseFilter baseFilter) {
        Pageable pageable = PageRequest.of(baseFilter.getPage(), baseFilter.getSize());
        return journalDao.findAllByProfileId(idProfile, pageable);
    }

    @Override
    public List<Journal> getOneDay(Long idProfile, long day) {
        LocalDateTime startDay = Instant.ofEpochMilli(day).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endStart = startDay.plusDays(1);
        return journalDao.findAllByProfileIdAndDtCreateBetween(idProfile, startDay, endStart);
    }


    @Override
    public void update(Journal updateJournal, Long idFood, Long idProfile, long dtUpdate) {
        Profile profile = profileService.get(idProfile);

        updateJournal.setDtUpdate(LocalDateTime.now());
        updateJournal.setDtCreate(updateJournal.getDtCreate());
        updateJournal.setProfile(profile);
        updateJournal.setId(idFood);

        journalDao.save(updateJournal);
    }

    @Override
    public void delete(Long idFood, Long idProfile, long updateJournal) {
        journalDao.deleteById(idFood);
    }
}

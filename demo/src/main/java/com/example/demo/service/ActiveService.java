package com.example.demo.service;

import com.example.demo.dao.api.IActiveDao;
import com.example.demo.dao.api.IProfileDao;
import com.example.demo.dto.filter.ActiveFilter;
import com.example.demo.model.Active;
import com.example.demo.model.Profile;
import com.example.demo.model.Weight;
import com.example.demo.service.api.IActiveService;
import com.example.demo.service.api.IProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ActiveService implements IActiveService {
    private final IActiveDao activeDao;
    private final IProfileService profileService;

    public ActiveService(IActiveDao activeDao, IProfileService profileService) {
        this.activeDao = activeDao;
        this.profileService = profileService;
    }

    @Override
    public Page<Active> getAll(ActiveFilter activeFilter, Long idProfile) {
        Pageable pageable = PageRequest.of(activeFilter.getPage(), activeFilter.getSize());
        return activeDao.findAllByProfileId(idProfile, pageable);
    }

    @Override
    public void save(Active active, Long idProfile) {
        Profile profile = profileService.get(idProfile);
        LocalDateTime time = LocalDateTime.now();

        active.setProfile(profile);
        active.setDtCreate(time);
        active.setDtUpdate(time);

        activeDao.save(active);
    }

    @Override
    public Active get(Long idActive, Long idProfile) {

        Active active = activeDao.findByProfileIdAndId(idProfile, idActive);
        if (active == null) {
            throw new IllegalArgumentException("По заданым id не существует записи в базе ");
        }
        return active;
    }

    @Override
    public void update(Active updateActive, Long idActive, Long idProfile, long dtUpdate) {
        Profile profile = profileService.get(idProfile);
        Active active = get(idActive, idProfile);

        updateActive.setProfile(profile);
        updateActive.setDtCreate(active.getDtCreate());
        updateActive.setDtUpdate(LocalDateTime.now());
        updateActive.setId(idActive);

        activeDao.save(updateActive);
    }

    @Override
    public void delete(Long idActive, Long idProfile, long dtUpdate) {
        Active deleteActive = get(idActive, idProfile);
        activeDao.delete(deleteActive);
    }
}

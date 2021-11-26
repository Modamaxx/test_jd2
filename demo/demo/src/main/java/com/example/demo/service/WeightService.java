package com.example.demo.service;

import com.example.demo.dao.api.IWeightDao;
import com.example.demo.dto.filter.WeightFilter;
import com.example.demo.model.Profile;
import com.example.demo.model.Weight;
import com.example.demo.service.api.IProfileService;
import com.example.demo.service.api.IWeightService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class WeightService implements IWeightService {
    private final IWeightDao weightDao;
    private final IProfileService profileService;

    public WeightService(IWeightDao weightDao, IProfileService profileService) {
        this.weightDao = weightDao;
        this.profileService = profileService;
    }

    @Override
    public Page<Weight> getAll(WeightFilter filter, Long idProfile) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());
        if (filter.getDtStart() != null && filter.getDtEnd() != null) {
            LocalDateTime start = Instant.ofEpochMilli(filter.getDtStart()).atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime end = Instant.ofEpochMilli(filter.getDtEnd()).atZone(ZoneId.systemDefault()).toLocalDateTime();
            return weightDao.findAllByProfileIdAndDtCreateBetween(idProfile, start, end, pageable);
        }
        return weightDao.findAllByProfileId(idProfile, pageable);
    }

    @Override
    public void save(Weight weight, Long idProfile) {
        Profile profile = profileService.get(idProfile);
        LocalDateTime time = LocalDateTime.now();

        weight.setProfile(profile);
        weight.setDtCreate(time);
        weight.setDtUpdate(time);

        weightDao.save(weight);
    }

    @Override
    public Weight get(Long idWeight, Long idProfile) {
        Weight weight = weightDao.findByProfileIdAndId(idProfile, idWeight);

        if (weight == null) {
            throw new IllegalArgumentException("По заданым id не существует записи в базе ");
        }
        return weight;
    }

    @Override
    public void update(Weight updateWeight, Long idWeight, Long idProfile, long dtUpdate) {
        Profile profile = profileService.get(idProfile);
        Weight weight = get(idWeight, idProfile);

        updateWeight.setProfile(profile);
        updateWeight.setDtCreate(weight.getDtCreate());
        updateWeight.setDtUpdate(LocalDateTime.now());
        updateWeight.setId(idWeight);

        weightDao.save(updateWeight);
    }

    @Override
    public void delete(Long idWeight, Long idProfile, long dtUpdate) {
        Weight deleteWeight = new Weight();
        deleteWeight.setId(idWeight);
        Profile profile = profileService.get(idProfile);
        deleteWeight.setProfile(profile);
        weightDao.delete(deleteWeight);
    }
}

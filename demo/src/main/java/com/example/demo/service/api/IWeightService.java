package com.example.demo.service.api;

import com.example.demo.dto.filter.WeightFilter;
import com.example.demo.model.Weight;
import org.springframework.data.domain.Page;

public interface IWeightService {
    Page<Weight> getAll(WeightFilter weightFilter, Long idProfile);

    void save(Weight weight, Long idProfile);

    Weight get(Long idWeight, Long idProfile);

    void update(Weight updateWeight, Long idWeight, Long idProfile,long dtUpdate);

    void delete(Long idWeight, Long idProfile,long dtUpdate);
}

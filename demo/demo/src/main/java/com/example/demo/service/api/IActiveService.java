package com.example.demo.service.api;

import com.example.demo.dto.filter.ActiveFilter;
import com.example.demo.model.Active;
import org.springframework.data.domain.Page;

public interface IActiveService {
    Page<Active> getAll(ActiveFilter activeFilter,Long idProfile);

    void save(Active active,Long idProfile);

    Active get(Long idActive,Long idProfile);

    void update(Active updateActive,Long idActive,Long idProfile,long dtUpdate);

    void delete(Long idActive,Long idProfile,long dtUpdate);
}

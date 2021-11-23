package com.example.demo.dao.api;

import com.example.demo.model.Active;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActiveDao extends JpaRepository<Active, Long> {
    Page<Active> findAllByProfileId(Long idProfile, Pageable pageable);

    Active findByProfileIdAndId(Long idProfile, Long idWeight);
}

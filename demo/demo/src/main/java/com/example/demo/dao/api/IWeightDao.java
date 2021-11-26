package com.example.demo.dao.api;

import com.example.demo.model.Active;
import com.example.demo.model.Journal;
import com.example.demo.model.Weight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface IWeightDao extends JpaRepository<Weight, Long> {
    Page<Weight> findAllByProfileId(Long idProfile, Pageable pageable);

    Weight findByProfileIdAndId(Long idProfile, Long idWeight);

    Page<Weight> findAllByProfileIdAndDtCreateBetween(Long idProfile, LocalDateTime dtStart,
                                                     LocalDateTime dtEnd, Pageable page);
}

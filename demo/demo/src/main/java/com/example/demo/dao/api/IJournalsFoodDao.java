package com.example.demo.dao.api;

import com.example.demo.model.Journal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IJournalsFoodDao extends JpaRepository<Journal, Long> {

    Page<Journal> findAllByProfileId(Long profileId, Pageable pageable);

    List<Journal> findAllByProfileIdAndDtCreateBetween(Long profileId, LocalDateTime dayStart, LocalDateTime dayEnd);

    Journal findByProfileIdAndId(Long idProfile, Long idFood);
}

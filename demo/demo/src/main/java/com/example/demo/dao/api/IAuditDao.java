package com.example.demo.dao.api;

import com.example.demo.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAuditDao extends JpaRepository<Audit,Long> {
    List<Audit> findAllByUserId(Long userId);
}

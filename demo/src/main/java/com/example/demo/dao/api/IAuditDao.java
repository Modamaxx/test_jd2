package com.example.demo.dao.api;

import com.example.demo.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuditDao extends JpaRepository<Audit,Long> {
}

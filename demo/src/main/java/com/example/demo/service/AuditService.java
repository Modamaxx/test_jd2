package com.example.demo.service;

import com.example.demo.dao.api.IAuditDao;
import com.example.demo.dto.filter.BaseFilter;
import com.example.demo.model.Audit;
import com.example.demo.service.api.IAuditService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuditService implements IAuditService {
    private final IAuditDao auditDao;

    public AuditService(IAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    @Override
    public Page<Audit> getAll(BaseFilter filter) {
        return null;
    }

    @Override
    public void save(Audit audit) {
        auditDao.save(audit);
    }


    @Override
    public Audit get(Long id) {
        return auditDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Active по id не найден"));
    }

    @Override
    public void update(Audit updateAudit) {
        auditDao.save(updateAudit);
    }

    @Override
    public void delete(Long id) {

    }

}

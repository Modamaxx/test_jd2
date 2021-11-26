package com.example.demo.service.api;

import com.example.demo.dto.filter.BaseFilter;
import com.example.demo.model.Audit;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAuditService {
    void update(Audit audit);

    void save(Audit audit);

    void delete(Long id);

    List<Audit> get(Long id);


    List<Audit> getAll(BaseFilter filter);
}

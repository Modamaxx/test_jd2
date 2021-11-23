package com.example.demo.service.api;

import com.example.demo.dto.filter.BaseFilter;
import org.springframework.data.domain.Page;

public interface IService<T, ID> {

    void save(T object);

    void delete(ID id,long dtUpdate);

    T get(ID id);

    void update(T object,Long id,long dtUpdate);

    Page<T> getAll(BaseFilter filter);
}

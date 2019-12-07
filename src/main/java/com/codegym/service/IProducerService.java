package com.codegym.service;

import com.codegym.model.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProducerService {
    Iterable<Producer> findAll();
    Page<Producer> findAll(Pageable pageable);
    Producer findById(Long id);
    void save(Producer producer);
    void remove(Long id);
}

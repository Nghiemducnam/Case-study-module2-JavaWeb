package com.codegym.service.impl;

import com.codegym.model.Producer;
import com.codegym.repository.IProducerRepository;
import com.codegym.service.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProducerService implements IProducerService {
    @Autowired
    private IProducerRepository producerRepository;

    @Override
    public Iterable<Producer> findAll() {
        return producerRepository.findAll();
    }

    @Override
    public Page<Producer> findAll(Pageable pageable) {
        return producerRepository.findAll(pageable);
    }

    @Override
    public Producer findById(Long id) {
        return producerRepository.findOne(id);
    }

    @Override
    public void save(Producer producer) {
        producerRepository.save(producer);
    }

    @Override
    public void remove(Long id) {
        producerRepository.delete(id);
    }
}

package com.codegym.service;

import com.codegym.model.Bill;
import com.codegym.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBillService {
    Iterable<Bill> findAll();
    Page<Bill> findAll (Pageable pageable);
    Bill findById(Long id);
    void save(Bill bill);
    void remove (Long id);
    Page<Bill> findAllByEmployee (Employee employee, Pageable pageable);
}

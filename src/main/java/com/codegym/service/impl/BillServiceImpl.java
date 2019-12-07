package com.codegym.service.impl;

import com.codegym.model.Bill;
import com.codegym.model.Employee;
import com.codegym.repository.IBillRepository;
import com.codegym.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BillServiceImpl implements IBillService {
    @Autowired
    private IBillRepository billRepository;

    @Override
    public Iterable<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Page<Bill> findAll(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    @Override
    public Bill findById(Long id) {
        return billRepository.findOne(id);
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public void remove(Long id) {
        billRepository.delete(id);
    }

    @Override
    public Page<Bill> findAllByEmployee(Employee employee, Pageable pageable) {
        return billRepository.findAllByEmployee(employee,pageable);
    }
}

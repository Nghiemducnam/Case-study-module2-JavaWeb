package com.codegym.service.impl;

import com.codegym.model.BillDetail;
import com.codegym.repository.IBillDetailRepository;
import com.codegym.service.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BillDetailServiceImpl implements IBillDetailService {
    @Autowired
    private IBillDetailRepository billDetailRepository;
    @Override
    public Iterable<BillDetail> findAll() {
        return billDetailRepository.findAll();
    }

    @Override
    public Page<BillDetail> findAll(Pageable pageable) {
        return billDetailRepository.findAll(pageable);
    }

    @Override
    public BillDetail findById(Long id) {
        return billDetailRepository.findOne(id);
    }

    @Override
    public void save(BillDetail billDetail) {
        billDetailRepository.save(billDetail);
    }

    @Override
    public void remove(Long id) {
        billDetailRepository.delete(id);
    }
}

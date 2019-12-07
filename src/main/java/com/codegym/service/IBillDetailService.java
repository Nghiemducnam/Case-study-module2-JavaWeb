package com.codegym.service;

import com.codegym.model.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBillDetailService {
    Iterable<BillDetail> findAll();
    Page<BillDetail> findAll(Pageable pageable);
    BillDetail findById(Long id);
    void save(BillDetail billDetail);
    void remove(Long id);
}

package com.codegym.service;

import com.codegym.model.BillDetail;
import com.codegym.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IpaymentService {
    Iterable<Payment> findAll();
    Page<Payment> findAll(Pageable pageable);
    Payment findById(Long id);
    void save(Payment payment);
    void remove(Long id);
    Page<Payment> findAllByBillDetails (BillDetail billDetail, Pageable pageable);

}

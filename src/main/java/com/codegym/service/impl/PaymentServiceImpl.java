package com.codegym.service.impl;

import com.codegym.model.BillDetail;
import com.codegym.model.Payment;
import com.codegym.repository.IPaymentRepository;
import com.codegym.service.IpaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PaymentServiceImpl implements IpaymentService {
    @Autowired
    private IPaymentRepository paymentRepository;


    @Override
    public Iterable<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Page<Payment> findAll(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findOne(id);
    }

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public void remove(Long id) {
        paymentRepository.delete(id);
    }

    @Override
    public Page<Payment> findAllByBillDetails(BillDetail billDetail, Pageable pageable) {
        return paymentRepository.findAllByBillDetails(billDetail,pageable);
    }
}

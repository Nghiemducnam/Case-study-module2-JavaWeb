package com.codegym.repository;

import com.codegym.model.BillDetail;
import com.codegym.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPaymentRepository extends PagingAndSortingRepository<Payment,Long> {
    Page<Payment> findAllByBillDetails (BillDetail billDetail, Pageable pageable);
}

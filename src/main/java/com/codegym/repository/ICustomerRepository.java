package com.codegym.repository;

import com.codegym.model.BillDetail;
import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICustomerRepository extends PagingAndSortingRepository <Customer, Long> {
    Page<Customer> findAllByBillDetails (BillDetail billDetail, Pageable pageable);
}

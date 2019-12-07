package com.codegym.repository;

import com.codegym.model.Bill;
import com.codegym.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBillRepository extends PagingAndSortingRepository<Bill,Long> {
    Page<Bill> findAllByEmployee (Employee employee, Pageable pageable);
}

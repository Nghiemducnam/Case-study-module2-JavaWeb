package com.codegym.service;

import com.codegym.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService {
    Iterable<Employee> findAll();
    Page<Employee> findAll(Pageable pageable);
    Employee findById(Long id);
    void save (Employee employee);
    void remove(Long id);
}

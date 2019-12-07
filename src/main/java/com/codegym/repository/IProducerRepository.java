package com.codegym.repository;

import com.codegym.model.Producer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProducerRepository extends PagingAndSortingRepository<Producer,Long> {
}

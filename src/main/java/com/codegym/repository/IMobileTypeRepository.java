package com.codegym.repository;

import com.codegym.model.MobileType;
import com.codegym.model.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IMobileTypeRepository extends PagingAndSortingRepository<MobileType,Long> {
//    Page<MobileType> findAllByProducer(Producer producer, Pageable pageable);
}

package com.codegym.repository;

import com.codegym.model.Mobile;
import com.codegym.model.MobileType;
import com.codegym.model.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IMobileRepository extends PagingAndSortingRepository<Mobile,Long> {
    Page<Mobile> findAllByProducer (Producer producer, Pageable pageable);
    Page<Mobile> findAllByMobileType (MobileType mobileType, Pageable pageable);
    Page<Mobile> findAllByMobileName(String mobileName, Pageable pageable);
//    Page<Mobile> findAllByMobileType (String mobileType, Pageable pageable);

}

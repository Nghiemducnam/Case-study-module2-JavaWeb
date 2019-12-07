package com.codegym.service;

import com.codegym.model.Mobile;
import com.codegym.model.MobileType;
import com.codegym.model.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMobileTypeService {
    Iterable<MobileType> findAll();
    Page<MobileType> findAll(Pageable pageable);
    MobileType findById(Long id);
    void save (MobileType mobileType);
    void remove (Long id);



}

package com.codegym.service.impl;

import com.codegym.model.Mobile;
import com.codegym.model.MobileType;
import com.codegym.model.Producer;
import com.codegym.repository.IMobileTypeRepository;
import com.codegym.service.IMobileTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class MobileTypeServiceImpl implements IMobileTypeService {
    @Autowired
    private IMobileTypeRepository mobileTypeRepository;

    @Override
    public Iterable<MobileType> findAll() {
        return mobileTypeRepository.findAll();
    }

    @Override
    public Page<MobileType> findAll(Pageable pageable) {
        return mobileTypeRepository.findAll(pageable);
    }

    @Override
    public MobileType findById(Long id) {
        return mobileTypeRepository.findOne(id);
    }

    @Override
    public void save(MobileType mobileType) {
        mobileTypeRepository.save(mobileType);
    }

    @Override
    public void remove(Long id) {
        mobileTypeRepository.delete(id);
    }

//    @Override
//    public Page<MobileType> findAllByProducer(Producer producer, Pageable pageable) {
//        return mobileTypeRepository.findAllByProducer(producer,pageable);
//    }

}

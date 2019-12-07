package com.codegym.repository;

import com.codegym.model.Bill;
import com.codegym.model.BillDetail;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBillDetailRepository extends PagingAndSortingRepository <BillDetail, Long> {

}

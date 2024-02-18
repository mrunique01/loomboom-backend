package com.loomboom.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.loomboom.model.Discount;

public interface DiscountService {

    List<Discount> getAllDiscount();

    Page<Discount> getAllDiscountByPage(Pageable pageable);

    Discount createDiscount(Discount contact);

    Discount updateDiscount(Discount contact, Long id);

    Boolean deleteDiscountById(Long id);

    Discount findByTitle(String title);

    Discount findById(Long id);
}
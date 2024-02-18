package com.loomboom.service.impl;

import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.Discount;
import com.loomboom.repository.DiscountRepository;
import com.loomboom.service.DiscountService;
import lombok.RequiredArgsConstructor;
import static com.loomboom.utils.StringUtils.*;

import java.util.List;

import static com.loomboom.contants.ErrorMessage.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    @Override
    public List<Discount> getAllDiscount() {
        return discountRepository.findAll();
    }

    @Override
    public Page<Discount> getAllDiscountByPage(Pageable pageable) {
        return discountRepository.findAll(pageable);
    }

    @Override
    public Discount createDiscount(Discount discount) {
        if (findByTitle(discount.getTitle()) != null) {
            throw new ApiRequestException(DUPLICATE_VALUE, HttpStatus.BAD_REQUEST);
        }
        return discountRepository.save(discount);
    }

    @Override
    public Discount updateDiscount(Discount discount, Long id) {
        if (findById(id) == null) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        Discount duplicateDiscount = findByTitle(discount.getTitle());

        if (duplicateDiscount != null && !duplicateDiscount.getId().equals(id)) {
            throw new ApiRequestException(DUPLICATE_VALUE, HttpStatus.BAD_REQUEST);
        }
        discount.setId(id);
        return discountRepository.save(discount);
    }

    @Override
    public Boolean deleteDiscountById(Long id) {
        Discount discount = findById(id);
        if (discount == null) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        discountRepository.deleteById(id);
        return true;

    }

    @Override
    public Discount findByTitle(String title) {
        if (!empty(title)) {
            return discountRepository.findByTitle(title).orElse(null);
        }
        return null;
    }

    @Override
    public Discount findById(Long id) {
        System.out.println(id+" fksjdhfkj");
        if (!empty(id)) {
            return discountRepository.findById(id).orElse(null);
        }
        return null;
    }

  

}

package com.loomboom.service.impl;

import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.ShippingDetail;
import com.loomboom.repository.ShippingDetailRepository;
import com.loomboom.service.ShippingDetailService;
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
public class ShippingDetailServiceImpl implements ShippingDetailService {

    private final ShippingDetailRepository shippingDetailRepository;

    @Override
    public List<ShippingDetail> getAllShippingDetail() {
        return shippingDetailRepository.findAll();
    }

    @Override
    public Page<ShippingDetail> getAllShippingDetailByPage(Pageable pageable) {
        return shippingDetailRepository.findAll(pageable);
    }

    @Override
    public ShippingDetail createShippingDetail(ShippingDetail shippingDetail) {
        return shippingDetailRepository.save(shippingDetail);
    }

    @Override
    public ShippingDetail updateShippingDetail(ShippingDetail shippingDetail, Long id) {
        if (findById(id) == null) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }

        shippingDetail.setId(id);
        return shippingDetailRepository.save(shippingDetail);
    }

    @Override
    public Boolean deleteShippingDetailById(Long id) {
        ShippingDetail shippingDetail = findById(id);
        if (shippingDetail == null) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        shippingDetailRepository.deleteById(id);
        return true;

    }

    @Override
    public ShippingDetail findById(Long id) {
        if (!empty(id)) {
            return shippingDetailRepository.findById(id).orElse(null);
        }
        return null;
    }

  

}

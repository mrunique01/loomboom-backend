package com.loomboom.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.loomboom.model.ShippingDetail;

public interface ShippingDetailService {

    List<ShippingDetail> getAllShippingDetail();

    Page<ShippingDetail> getAllShippingDetailByPage(Pageable pageable);

    ShippingDetail createShippingDetail(ShippingDetail shippingDetail);

    ShippingDetail updateShippingDetail(ShippingDetail shippingDetail, Long id);

    Boolean deleteShippingDetailById(Long id);

    ShippingDetail findById(Long id);
}
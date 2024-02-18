package com.loomboom.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import static com.loomboom.contants.PathConstants.*;
import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.shipping.ShippingDetailRequest;
import com.loomboom.dto.shipping.ShippingDetailResponse;
import com.loomboom.dto.shipping.ShippingDetailsResponse;
import com.loomboom.mapper.ShippingDetailMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class ShippingDetailController {

    private final ShippingDetailMapper shippingDetailMapper;

    @GetMapping(ALL_SHIPPING_DETAILS)
    public ResponseEntity<ShippingDetailsResponse> getShippingDetails() {
        ShippingDetailsResponse shippingDetails = shippingDetailMapper.getAllShippingDetail();
        return ResponseEntity.ok(shippingDetails);
    }

    @GetMapping(ALL_SHIPPING_DETAILS_BY_PAGE)
    public ResponseEntity<ShippingDetailsResponse> getShippingDetailsByPage(Pageable pageble) {
        ShippingDetailsResponse shippingDetails = shippingDetailMapper.getAllShippingDetailByPage(pageble);
        return ResponseEntity.ok(shippingDetails);
    }

    @GetMapping(SHIPPING_DETAIL_BY_ID)
    public ResponseEntity<ShippingDetailResponse> getShippingDetailById(@PathVariable Long shippingDetailId) {
        ShippingDetailResponse shippingDetail = shippingDetailMapper.findShippingDetailById(shippingDetailId);
        return ResponseEntity.ok(shippingDetail);
    }

    @PostMapping(CREATE_SHIPPING_DETAIL)
    public ResponseEntity<ShippingDetailResponse> createShippingDetail(
            @RequestBody @Valid ShippingDetailRequest shippingDetailRequest) {
        ShippingDetailResponse shippingDetailResponse = shippingDetailMapper
                .createShippingDetail(shippingDetailRequest);
        return ResponseEntity.ok(shippingDetailResponse);
    }

    @PutMapping(UPDATE_SHIPPING_DETAIL)
    public ResponseEntity<ShippingDetailResponse> updateShippingDetail(@PathVariable Long shippingDetailId,
            @RequestBody @Valid ShippingDetailRequest shippingDetailRequest) {

        ShippingDetailResponse shippingDetail = shippingDetailMapper.updateShippingDetail(shippingDetailId,
                shippingDetailRequest);
        return ResponseEntity.ok(shippingDetail);
    }

    @DeleteMapping(DELETE_SHIPPING_DETAIL)
    public ResponseEntity<ApiResponse> deleteShippingDetail(@PathVariable Long shippingDetailId) {
        ApiResponse response = shippingDetailMapper.deleteShippingDetail(shippingDetailId);
        return ResponseEntity.ok(response);
    }

}

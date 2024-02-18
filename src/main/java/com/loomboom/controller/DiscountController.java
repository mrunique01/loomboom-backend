package com.loomboom.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static com.loomboom.contants.PathConstants.*;
import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.discount.DiscountRequest;
import com.loomboom.dto.discount.DiscountResponse;
import com.loomboom.dto.discount.DiscountsResponse;
import com.loomboom.mapper.DiscountMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@RestController
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountMapper discountMapper;

    @GetMapping(ALL_DISCOUNTS)
    public ResponseEntity<DiscountsResponse> getDiscounts() {
        DiscountsResponse discounts = discountMapper.getAllDiscount();
        return ResponseEntity.ok(discounts);
    }

    @GetMapping(ALL_DISCOUNTS_BY_PAGE)
    public ResponseEntity<DiscountsResponse> getDiscountsByPage(Pageable pageble) {
        DiscountsResponse discounts = discountMapper.getAllDiscountByPage(pageble);
        return ResponseEntity.ok(discounts);
    }

    @GetMapping(DISCOUNT_BY_ID)
    public ResponseEntity<DiscountResponse> getDiscountById(@PathVariable Long discountId) {
        DiscountResponse discount = discountMapper.findDiscountById(discountId);
        return ResponseEntity.ok(discount);
    }

    @PostMapping(CREATE_DISCOUNT)
    public ResponseEntity<DiscountResponse> createDiscount(
            @RequestBody @Valid DiscountRequest discountRequest) {
        DiscountResponse discountResponse = discountMapper.createDiscount(discountRequest);
        return ResponseEntity.ok(discountResponse);
    }

    @PutMapping(UPDATE_DISCOUNT)
    public ResponseEntity<DiscountResponse> updateDiscount(@PathVariable Long discountId,
            @RequestBody @Valid DiscountRequest discountRequest) {

        DiscountResponse discount = discountMapper.updateDiscount(discountId, discountRequest);
        return ResponseEntity.ok(discount);
    }

    @DeleteMapping(DELETE_DISCOUNT)
    public ResponseEntity<ApiResponse> deleteDiscount(@PathVariable Long discountId) {
        ApiResponse response = discountMapper.deleteDiscount(discountId);
        return ResponseEntity.ok(response);
    }

}

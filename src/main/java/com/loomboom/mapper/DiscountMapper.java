package com.loomboom.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.pagination.PaginationReponse;
import com.loomboom.dto.discount.DiscountsResponse;
import com.loomboom.dto.discount.DiscountRequest;
import com.loomboom.dto.discount.DiscountResponse;
import com.loomboom.model.Discount;
import static com.loomboom.contants.SuccessMessage.*;
import com.loomboom.service.DiscountService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DiscountMapper {

    private final DiscountService discountService;
    private final CommonMapper commonMapper;

    public DiscountResponse createDiscount(DiscountRequest discountRequest) {

        Discount discount = commonMapper.mapObject(discountRequest, Discount.class);
        return commonMapper.mapObject(discountService.createDiscount(discount), DiscountResponse.class);
    }

    public DiscountsResponse getAllDiscount() {
        List<Discount> discount = discountService.getAllDiscount();
        List<DiscountResponse> discounts = commonMapper.mapListObject(discount, DiscountResponse.class);
        DiscountsResponse discountResponse = new DiscountsResponse();
        discountResponse.setDiscounts(discounts);
        discountResponse.setPagination(new PaginationReponse());
        return discountResponse;
    }

    public DiscountsResponse getAllDiscountByPage(Pageable pageable) {
        Page<Discount> discount = discountService.getAllDiscountByPage(pageable);
        List<DiscountResponse> discounts = commonMapper.mapListObject(discount.getContent(), DiscountResponse.class);
        DiscountsResponse discountResponse = new DiscountsResponse();
        discountResponse.setDiscounts(discounts);
        PaginationReponse paginationReponse = commonMapper.paginationData(discount);
        discountResponse.setPagination(paginationReponse);
        return discountResponse;
    }

    public DiscountResponse findDiscountById(Long id) {
        return commonMapper.mapObject(discountService.findById(id), DiscountResponse.class);
    }

    public DiscountResponse updateDiscount(Long id, DiscountRequest discountRequest) {
        Discount discount = commonMapper.mapObject(discountRequest, Discount.class);
        return commonMapper.mapObject(discountService.updateDiscount(discount, id), DiscountResponse.class);
    }

    public ApiResponse deleteDiscount(Long id) {
        discountService.deleteDiscountById(id);
        return new ApiResponse(true, CATEGORY_DELETED);
    }
}

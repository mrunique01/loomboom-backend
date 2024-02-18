package com.loomboom.dto.discount;

import java.util.List;

import com.loomboom.dto.pagination.PaginationReponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountsResponse {
    List<DiscountResponse> discounts;
    PaginationReponse pagination;
}

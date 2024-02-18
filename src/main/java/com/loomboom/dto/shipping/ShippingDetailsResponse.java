package com.loomboom.dto.shipping;

import java.util.List;

import com.loomboom.dto.pagination.PaginationReponse;

public class ShippingDetailsResponse {
    List<ShippingDetailResponse> categories;
    PaginationReponse pagination;
}

package com.loomboom.dto.shipping;

import java.util.List;

import com.loomboom.dto.pagination.PaginationReponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingDetailsResponse {
    List<ShippingDetailResponse> shippingDetails;
    PaginationReponse pagination;
}

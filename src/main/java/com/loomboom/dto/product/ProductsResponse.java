package com.loomboom.dto.product;

import java.util.List;

import com.loomboom.dto.pagination.PaginationReponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductsResponse {
    List<ProductResponse> products;
    PaginationReponse pagination;
}

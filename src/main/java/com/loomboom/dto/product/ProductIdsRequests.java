package com.loomboom.dto.product;

import static com.loomboom.contants.ErrorMessage.REQUIRED_FIELD;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public class ProductIdsRequests {

    @NotEmpty(message = REQUIRED_FIELD)
    List<String> productIds;
}
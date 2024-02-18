package com.loomboom.dto.order;

import static com.loomboom.contants.ErrorMessage.REQUIRED_FIELD;

import com.loomboom.model.Product;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderItemRequest {
    @NotNull(message = REQUIRED_FIELD)
    private Product product;
    @NotNull(message = REQUIRED_FIELD)
    private Integer quantity;
    @NotNull(message = REQUIRED_FIELD)
    private Double price;
    @NotNull(message = REQUIRED_FIELD)
    private Long id;
}

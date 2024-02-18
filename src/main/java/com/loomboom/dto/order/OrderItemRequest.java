package com.loomboom.dto.order;

import static com.loomboom.contants.ErrorMessage.REQUIRED_FIELD;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    @NotNull(message = REQUIRED_FIELD)
    private Long productId;
    @NotNull(message = REQUIRED_FIELD)
    private Integer quantity;
    @NotNull(message = REQUIRED_FIELD)
    private Double price;
}

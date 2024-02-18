package com.loomboom.dto.shipping;

import static com.loomboom.contants.ErrorMessage.REQUIRED_FIELD;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateShippingDetailRequest extends ShippingDetailRequest {

    @NotNull(message = REQUIRED_FIELD)
    private Long userId;
    @NotNull(message = REQUIRED_FIELD)
    private Long orderId;

}

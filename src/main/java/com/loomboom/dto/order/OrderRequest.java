package com.loomboom.dto.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.loomboom.contants.ErrorMessage.MAX_500_CHAR;
import static com.loomboom.contants.ErrorMessage.MIN_ONE_ITEM;
import static com.loomboom.contants.ErrorMessage.REQUIRED_FIELD;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.loomboom.dto.shipping.ShippingDetailRequest;

@Getter
@Setter
@ToString
public class OrderRequest {

    @NotNull(message = REQUIRED_FIELD)
    private Double totalAmount;

    @NotNull(message = REQUIRED_FIELD)
    private Double shippingAmount;

    @NotNull(message = REQUIRED_FIELD)
    private Double discountAmount;
    @NotNull(message = REQUIRED_FIELD)
    private Double subTotal;
    @NotEmpty(message = REQUIRED_FIELD)
    private String paymentMethod;

    @NotNull(message = REQUIRED_FIELD)
    private Long userId;

    @NotNull(message = REQUIRED_FIELD)
    private @Valid ShippingDetailRequest shippingDetails;

    @NotEmpty(message = REQUIRED_FIELD)
    @Size(min = 1, message = MIN_ONE_ITEM)
    private List<@Valid OrderItemRequest> orderItems;

    @Length(max = 500, message = MAX_500_CHAR)
    private String additionalNote;

}

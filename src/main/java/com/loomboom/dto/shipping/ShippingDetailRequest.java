package com.loomboom.dto.shipping;

import static com.loomboom.contants.ErrorMessage.REQUIRED_FIELD;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingDetailRequest {

    // @Null(message = REQUIRED_FIELD)
    private Long id;
    @NotBlank(message = REQUIRED_FIELD)
    private String firstName;
    @NotBlank(message = REQUIRED_FIELD)
    private String lastName;
    @NotBlank(message = REQUIRED_FIELD)
    private String email;
    @NotBlank(message = REQUIRED_FIELD)
    private String phone;
    @NotBlank(message = REQUIRED_FIELD)
    private String houseNo;
    @NotBlank(message = REQUIRED_FIELD)
    private String street;
    @NotBlank(message = REQUIRED_FIELD)
    private String city;
    @NotBlank(message = REQUIRED_FIELD)
    private String state;
    @NotBlank(message = REQUIRED_FIELD)
    private String country;
    @NotNull(message = REQUIRED_FIELD)
    private boolean isDefault;
    @NotBlank(message = REQUIRED_FIELD)
    private String zip;
    
}

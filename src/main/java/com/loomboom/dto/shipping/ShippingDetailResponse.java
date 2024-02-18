package com.loomboom.dto.shipping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingDetailResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String houseNo;

    private String street;

    private String city;

    private String state;

    private String country;

    private boolean isDefault;

    private String zip;
}

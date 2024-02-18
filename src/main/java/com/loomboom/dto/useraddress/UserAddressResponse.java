package com.loomboom.dto.useraddress;

import com.loomboom.dto.shipping.ShippingDetailResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddressResponse extends ShippingDetailResponse {
    private Long userId;
}

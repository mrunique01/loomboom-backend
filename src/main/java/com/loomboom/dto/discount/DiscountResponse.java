package com.loomboom.dto.discount;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountResponse {

    private Long id;
    private String title;
    private String type;
    private Double value;
    private Double expireAt;
    private Integer active;
}

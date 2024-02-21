package com.loomboom.dto.discount;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountResponse {

    private Long id;
    private String title;
    private String type;
    private Integer value;
    private Date expireAt;
    private Boolean active;
}

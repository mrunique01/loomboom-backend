package com.loomboom.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Long id;

    private String title;

    private String description;

    private String price;

    private String category;

    private String thumbnail;

    private Integer active;
}

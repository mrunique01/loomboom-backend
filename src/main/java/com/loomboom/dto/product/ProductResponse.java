package com.loomboom.dto.product;

import java.util.List;

import com.loomboom.model.Category;
import com.loomboom.model.ProductImage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Long id;

    private String title;

    private String description;

    private String price;

    private Category category;

    private List<ProductImage> productImages;

    private String thumbnail;

    private Integer active;

    private Float rating;
}

package com.loomboom.dto.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {

    private Long id;

    private String title;

    private Integer active;
}

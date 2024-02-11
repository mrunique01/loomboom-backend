package com.loomboom.dto.category;

import java.util.List;

import com.loomboom.dto.pagination.PaginationReponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriesResponse {
    List<CategoryResponse> categories;
    PaginationReponse pagination;
}

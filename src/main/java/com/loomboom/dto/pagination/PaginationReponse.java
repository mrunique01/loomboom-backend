package com.loomboom.dto.pagination;

import lombok.Data;

@Data
public class PaginationReponse {
    private int size;
    private int page;
    private Long totalElements;
}

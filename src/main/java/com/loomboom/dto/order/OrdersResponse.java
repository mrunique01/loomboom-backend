package com.loomboom.dto.order;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import com.loomboom.dto.pagination.PaginationReponse;

@Getter
@Setter
public class OrdersResponse {

    List<OrderResponse> orders;
    PaginationReponse pagination;

}

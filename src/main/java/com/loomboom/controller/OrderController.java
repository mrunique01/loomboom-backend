package com.loomboom.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import static com.loomboom.contants.PathConstants.*;
import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.order.OrderRequest;
import com.loomboom.dto.order.OrderResponse;
import com.loomboom.dto.order.OrdersResponse;
import com.loomboom.mapper.OrderMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;

    @GetMapping(ALL_ORDERS)
    public ResponseEntity<OrdersResponse> getOrders() {
        OrdersResponse orders = orderMapper.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    @GetMapping(ALL_ORDERS_BY_PAGE)
    public ResponseEntity<OrdersResponse> getOrdersByPage(Pageable pageble) {
        OrdersResponse orders = orderMapper.getAllOrderByPage(pageble);
        return ResponseEntity.ok(orders);
    }

    @GetMapping(ORDER_BY_ID)
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        OrderResponse order = orderMapper.findOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @PostMapping(CREATE_ORDER)
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody @Valid OrderRequest orderRequest) {
                
        OrderResponse orderResponse = orderMapper.createOrder(orderRequest);
        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping(UPDATE_ORDER)
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long orderId,
            @RequestBody @Valid OrderRequest orderRequest) {

        OrderResponse order = orderMapper.updateOrder(orderId, orderRequest);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping(DELETE_ORDER)
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long orderId) {
        ApiResponse response = orderMapper.deleteOrder(orderId);
        return ResponseEntity.ok(response);
    }

}

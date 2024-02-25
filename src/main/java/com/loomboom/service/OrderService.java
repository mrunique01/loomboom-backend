package com.loomboom.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.loomboom.model.Order;

public interface OrderService {

    List<Order> getAllOrder();

    Page<Order> getAllOrderByPage(Pageable pageable);

    Order createOrder(Order order);

    Order updateOrder(Order order, Long id);

    Boolean deleteOrderById(Long id);

    Order findById(Long id);

    List<Order> findByUserId(Long userId);

    Order findOrderByUserId(Long orderId, Long userId);
}
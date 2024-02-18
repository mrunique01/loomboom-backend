package com.loomboom.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.loomboom.model.OrderItem;

public interface OrderItemService {

    List<OrderItem> getAllOrderItem();

    Page<OrderItem> getAllOrderItemByPage(Pageable pageable);

    List<OrderItem> createOrderItems(List<OrderItem> orderItems);

    OrderItem updateOrderItem(OrderItem orderItem, Long id);

    Boolean deleteOrderItemById(Long id);

    OrderItem findById(Long id);
}
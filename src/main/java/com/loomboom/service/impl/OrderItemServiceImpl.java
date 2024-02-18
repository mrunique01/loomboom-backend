package com.loomboom.service.impl;

import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.OrderItem;
import com.loomboom.repository.OrderItemRepository;
import com.loomboom.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import static com.loomboom.utils.StringUtils.*;

import java.util.List;

import static com.loomboom.contants.ErrorMessage.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getAllOrderItem() {
        return orderItemRepository.findAll();
    }

    @Override
    public Page<OrderItem> getAllOrderItemByPage(Pageable pageable) {
        return orderItemRepository.findAll(pageable);
    }

    @Override
    public List<OrderItem> createOrderItems(List<OrderItem> orderItems) {
        return orderItemRepository.saveAll(orderItems);
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem, Long id) {
        if (findById(id) == null) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }

        orderItem.setId(id);
        return orderItemRepository.save(orderItem);
    }

    @Override
    public Boolean deleteOrderItemById(Long id) {
        OrderItem orderItem = findById(id);
        if (orderItem == null) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        orderItemRepository.deleteById(id);
        return true;

    }

    @Override
    public OrderItem findById(Long id) {
        if (!empty(id)) {
            return orderItemRepository.findById(id).orElse(null);
        }
        return null;
    }

}

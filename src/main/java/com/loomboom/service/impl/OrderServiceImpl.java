package com.loomboom.service.impl;

import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.Order;
import com.loomboom.repository.OrderRepository;
import com.loomboom.service.OrderService;
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
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> getAllOrderByPage(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order createOrder(Order order) {
        System.out.println(order.toString() + 4520);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order, Long id) {
        if (findById(id) == null) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }

        order.setId(id);
        return orderRepository.save(order);
    }

    @Override
    public Boolean deleteOrderById(Long id) {
        Order order = findById(id);
        if (order == null) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        orderRepository.deleteById(id);
        return true;

    }

    @Override
    public Order findById(Long id) {
        if (!empty(id)) {
            return orderRepository.findById(id).orElse(null);
        }
        return null;
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    @Override
    public Order findOrderByUserId(Long orderId, Long userId) {
        return orderRepository.findOrderByIdAndUserId(orderId, userId);
    }

}

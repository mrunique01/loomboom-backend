package com.loomboom.repository;

import org.springframework.stereotype.Repository;
import com.loomboom.model.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByUserId(Long userId);

    Order findOrderByIdAndUserId(Long orderId, Long userId);

}

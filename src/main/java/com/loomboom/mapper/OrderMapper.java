package com.loomboom.mapper;

import java.util.List;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.pagination.PaginationReponse;
import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.dto.order.OrdersResponse;
import com.loomboom.dto.order.OrderRequest;
import com.loomboom.dto.order.OrderResponse;
import com.loomboom.model.Order;
import com.loomboom.model.OrderItem;
import com.loomboom.model.Product;
import com.loomboom.model.ShippingDetail;
import com.loomboom.model.User;
import com.loomboom.model.UserAddress;

import static com.loomboom.contants.ErrorMessage.NOT_EXISTS;
import static com.loomboom.contants.ErrorMessage.ORDER_ITEM_NOT_EXISTS;
import static com.loomboom.contants.ErrorMessage.USER_NOT_EXISTS;
import static com.loomboom.contants.SuccessMessage.*;

import com.loomboom.service.OrderItemService;
import com.loomboom.service.OrderService;
import com.loomboom.service.ProductService;
import com.loomboom.service.ShippingDetailService;
import com.loomboom.service.UserAddressService;
import com.loomboom.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderService orderService;
    private final ProductService productService;
    private final ShippingDetailService shippingDetailService;
    private final UserAddressService userAddressService;
    private final OrderItemService orderItemService;
    private final UserService userService;
    private final CommonMapper commonMapper;

    public OrderResponse createOrder(OrderRequest orderRequest) {

        User user = userService.getUserById(orderRequest.getUserId());
        if (user == null) {
            throw new ApiRequestException(USER_NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        ShippingDetail shippingDetail = commonMapper.mapObject(orderRequest.getShippingDetails(), ShippingDetail.class);
        orderRequest.setShippingDetails(null);
        Order order = commonMapper.mapObject(orderRequest, Order.class);
        shippingDetail.setUsers(user);
        order.setUser(user);
        UserAddress userAddress = commonMapper.mapObject(shippingDetail, UserAddress.class);
        shippingDetail = shippingDetailService.createShippingDetail(shippingDetail);
        userAddressService.createUserAddress(userAddress);
        order.setShippingDetails(shippingDetail);
        order.setOrderItems(null);
        order.setOrderDate(new Date());
        order.setStatus("Pending");
        Order finalOrder = orderService.createOrder(order);
        shippingDetail.setOrder(finalOrder);
        shippingDetail = shippingDetailService.createShippingDetail(shippingDetail);
        List<OrderItem> orderItems = orderRequest.getOrderItems().stream().map((orderItemRequest) -> {
            Product product = productService.findById(orderItemRequest.getProductId());
            if (product == null) {
                throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(finalOrder);
            orderItem.setPrice(orderItemRequest.getPrice());
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            return orderItem;
        }).collect(Collectors.toList());
        orderItemService.createOrderItems(orderItems);
        OrderResponse orderResponse = commonMapper.mapObject(order, OrderResponse.class);
        orderResponse.setId(finalOrder.getId());
        orderResponse.setOrderItems(orderItems);
        orderResponse.setShippingDetails(shippingDetail);
        return orderResponse;
    }

    public OrdersResponse getAllOrder() {
        List<Order> order = orderService.getAllOrder();
        List<OrderResponse> orders = commonMapper.mapListObject(order, OrderResponse.class);
        OrdersResponse orderResponse = new OrdersResponse();
        orderResponse.setOrders(orders);
        orderResponse.setPagination(new PaginationReponse());
        return orderResponse;
    }

    public OrdersResponse getAllOrderByPage(Pageable pageable) {
        Page<Order> order = orderService.getAllOrderByPage(pageable);
        List<OrderResponse> orders = commonMapper.mapListObject(order.getContent(), OrderResponse.class);
        OrdersResponse orderResponse = new OrdersResponse();
        orderResponse.setOrders(orders);
        PaginationReponse paginationReponse = commonMapper.paginationData(order);
        orderResponse.setPagination(paginationReponse);
        return orderResponse;
    }

    public OrderResponse findOrderById(Long id) {
        return commonMapper.mapObject(orderService.findById(id), OrderResponse.class);
    }

    public OrdersResponse findOrdersByUserId(Long userId) {
        return commonMapper.mapObject(orderService.findByUserId(userId), OrdersResponse.class);
    }
    public OrderResponse findOrderByUserId(Long orderId,Long userId) {
        return commonMapper.mapObject(orderService.findOrderByUserId(orderId,userId), OrderResponse.class);
    }
    

    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
        User user = userService.getUserById(orderRequest.getUserId());
        if (user == null) {
            throw new ApiRequestException(USER_NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        ShippingDetail shippingDetail = commonMapper.mapObject(orderRequest.getShippingDetails(), ShippingDetail.class);
        orderRequest.setShippingDetails(null);
        Order order = commonMapper.mapObject(orderRequest, Order.class);
        shippingDetail.setUsers(user);
        order.setUser(user);
        shippingDetail = shippingDetailService.updateShippingDetail(shippingDetail, shippingDetail.getId());
        order.setShippingDetails(shippingDetail);
        order.setOrderItems(null);
        order.setUpdateDate(new Date());
        Order finalOrder = orderService.updateOrder(order, id);
        shippingDetail.setOrder(finalOrder);
        shippingDetail = shippingDetailService.createShippingDetail(shippingDetail);
        List<OrderItem> orderItems = orderRequest.getOrderItems().stream().map((orderItemRequest) -> {
            Product product = productService.findById(orderItemRequest.getProductId());
            if (product == null) {
                throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
            }
            OrderItem orderItemData = orderItemService.findById(orderItemRequest.getId());
            if (orderItemData == null) {
                throw new ApiRequestException(ORDER_ITEM_NOT_EXISTS, HttpStatus.BAD_REQUEST);
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(finalOrder);
            orderItem.setPrice(orderItemRequest.getPrice());
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setId(orderItemData.getId());
            return orderItem;
        }).collect(Collectors.toList());
        orderItemService.createOrderItems(orderItems);
        OrderResponse orderResponse = commonMapper.mapObject(order, OrderResponse.class);
        orderResponse.setOrderItems(orderItems);
        orderResponse.setShippingDetails(shippingDetail);
        return orderResponse;

    }

    public ApiResponse deleteOrder(Long id) {
        orderService.deleteOrderById(id);
        return new ApiResponse(true, CATEGORY_DELETED);
    }
}

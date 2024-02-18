package com.loomboom.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import com.loomboom.model.OrderItem;
import com.loomboom.model.ShippingDetail;

@Getter
@Setter
public class OrderResponse {

    private Double totalAmount;

    private Double shippingAmount;

    private Double discountAmount;

    private String paymentMethod;

    private Long userId;

    private Long id;

    private Date orderDate;

    private ShippingDetail shippingDetails;

    private List<OrderItem> orderItems;

    private String additionalNote;
    
    private Date updateDate;

    private String status;

    private Double subTotal;

}

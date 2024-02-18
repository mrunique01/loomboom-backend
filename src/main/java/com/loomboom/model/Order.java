package com.loomboom.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "orders")
@Setter
@Getter
@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Double totalAmount;
    private Double shippingAmount;
    private Double discountAmount;
    private Double subTotal;
    
    @Column(columnDefinition = "varchar(255) default 'pending'")
    private String status;
    private String paymentMethod;
    @ManyToOne
    @JoinColumn(name = "addess_id")
    private ShippingDetail shippingDetails;
    @JsonManagedReference
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    private String additionalNote;
    @CreatedDate
    private Date orderDate;
    @UpdateTimestamp
    private Date updateDate;

}

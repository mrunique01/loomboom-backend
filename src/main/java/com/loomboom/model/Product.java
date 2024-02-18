package com.loomboom.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", unique = true)
    private String title;
    private String description;
    private Double price;
    private Float rating;
    private String thumbnail;
    private Integer active;
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductImage> productImages;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<OrderItem> orderItems;
}

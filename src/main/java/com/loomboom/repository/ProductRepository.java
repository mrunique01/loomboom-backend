package com.loomboom.repository;

import org.springframework.stereotype.Repository;

import com.loomboom.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByTitle(String title);
}

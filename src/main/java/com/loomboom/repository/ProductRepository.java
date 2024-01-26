package com.loomboom.repository;

import org.springframework.stereotype.Repository;

import com.loomboom.model.Product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByTitle(String title);
}

package com.loomboom.service;

import java.util.List;

import com.loomboom.model.Product;

public interface ProductService {

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product updateProduct(Product product, Long id);

    Boolean deleteProductById(Long id);

    Product findByTitle(String title);

    Product findById(Long id);
}
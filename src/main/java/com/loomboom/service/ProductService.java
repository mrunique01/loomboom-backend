package com.loomboom.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.loomboom.model.Product;

public interface ProductService {

    Page<Product> getAllProducts(Pageable pageable);

    Product createProduct(Product product,MultipartFile thumbnail);

    Product updateProduct(Product product, Long id,MultipartFile thumbnail);

    Boolean deleteProductById(Long id);

    Product findByTitle(String title);

    Product findById(Long id);
}
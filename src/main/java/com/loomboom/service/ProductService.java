package com.loomboom.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.loomboom.model.Product;
import com.loomboom.model.ProductImage;

public interface ProductService {

    Page<Product> getAllProducts(Pageable pageable);

    Product createProduct(Product product, MultipartFile thumbnail);

    Product updateProduct(Product product, Long id, MultipartFile thumbnail);

    ProductImage addProductImage(Long id, MultipartFile thumbnail);

    Boolean deleteProductById(Long id);

    Boolean deleteProductImageById(Long id);

    Product findByTitle(String title);

    List<Product> findByIds(List<Long> ids);

    Product findById(Long id);
}
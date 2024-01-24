package com.loomboom.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import static com.loomboom.contants.PathConstants.*;

import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.product.ProductRequest;
import com.loomboom.dto.product.ProductResponse;
import com.loomboom.mapper.ProductMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;

    @GetMapping(ALL_PRODUCTS)
    public ResponseEntity<List<ProductResponse>> getProducts() {

        List<ProductResponse> products = productMapper.getAllProducts();
        return ResponseEntity.ok(products);

    }

    @GetMapping(PRODUCT_BY_ID)
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId) {
        ProductResponse product = productMapper.findProductById(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping(CREATE_PRODUCT)
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody @Valid ProductRequest productRequest) {
        ProductResponse productResponse = productMapper.createProduct(productRequest);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping(UPDATE_PRODUCT)
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId,
            @RequestBody @Valid ProductRequest productRequest) {

        ProductResponse product = productMapper.updateProduct(productId, productRequest);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping(DELETE_PRODUCT)
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        ApiResponse response = productMapper.deleteProduct(productId);
        return ResponseEntity.ok(response);
    }

}

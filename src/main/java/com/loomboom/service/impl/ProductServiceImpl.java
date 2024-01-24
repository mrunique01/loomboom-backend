package com.loomboom.service.impl;

import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.Product;
import com.loomboom.repository.ProductRepository;
import com.loomboom.service.ProductService;
import lombok.RequiredArgsConstructor;
import static com.loomboom.utils.StringUtils.*;
import java.util.List;
import static com.loomboom.contants.ErrorMessage.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        if (findByTitle(product.getTitle()) != null) {
            throw new ApiRequestException(DUPLICATE_VALUE, HttpStatus.BAD_REQUEST);
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        if (empty(id) || (!empty(id) && findById(id) == null)) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        String productTitle = product.getTitle();
        if (!empty(productTitle) && findByTitle(productTitle) != null) {
            throw new ApiRequestException(DUPLICATE_VALUE, HttpStatus.BAD_REQUEST);
        }
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public Boolean deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (empty(id) || (!empty(id) && product == null)) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        productRepository.deleteById(id);
        return true;

    }

    @Override
    public Product findByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    @Override
    public Product findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (empty(id) || (!empty(id) && product == null)) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        return product;
    }

}

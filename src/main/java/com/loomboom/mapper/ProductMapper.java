package com.loomboom.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.product.ProductRequest;
import com.loomboom.dto.product.ProductResponse;
import com.loomboom.model.Product;
import static com.loomboom.contants.SuccessMessage.*;
import com.loomboom.service.ProductService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ProductService productService;
    private final CommonMapper commonMapper;

    public ProductResponse createProduct(ProductRequest productRequest) {

        Product product = commonMapper.mapObject(productRequest, Product.class);
        return commonMapper.mapObject(productService.createProduct(product), ProductResponse.class);
    }

    public List<ProductResponse> getAllProducts() {
        return commonMapper.mapListObject(productService.getAllProducts(), ProductResponse.class);
    }

    public ProductResponse findProductById(Long id) {
        return commonMapper.mapObject(productService.findById(id), ProductResponse.class);
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product = commonMapper.mapObject(productRequest, Product.class);
        return commonMapper.mapObject(productService.updateProduct(product, id), ProductResponse.class);
    }

    public ApiResponse deleteProduct(Long id) {
        productService.deleteProductById(id);
        return new ApiResponse(true, PRODUCT_DELETED);
    }
}

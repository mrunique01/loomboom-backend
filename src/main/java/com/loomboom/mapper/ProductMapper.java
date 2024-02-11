package com.loomboom.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.pagination.PaginationReponse;
import com.loomboom.dto.product.ProductRequest;
import com.loomboom.dto.product.ProductResponse;
import com.loomboom.dto.product.ProductsResponse;
import com.loomboom.model.Product;
import static com.loomboom.contants.SuccessMessage.*;

import com.loomboom.service.CategoryService;
import com.loomboom.service.ProductService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final CommonMapper commonMapper;

    public ProductResponse createProduct(ProductRequest productRequest, MultipartFile thumbnail) {
        Product product = commonMapper.mapObject(productRequest, Product.class);
        product.setCategory(categoryService.findById(productRequest.getCategory()));
        return commonMapper.mapObject(productService.createProduct(product, thumbnail), ProductResponse.class);
    }

    public ProductsResponse getAllProducts(Pageable pageable) {
        Page<Product> product = productService.getAllProducts(pageable);
        List<ProductResponse> products = commonMapper.mapListObject(product.getContent(), ProductResponse.class);
        ProductsResponse productsResponse = new ProductsResponse();
        productsResponse.setProducts(products);
        PaginationReponse paginationReponse = commonMapper.paginationData(product);
        productsResponse.setPagination(paginationReponse);
        return productsResponse;
    }

    public ProductResponse findProductById(Long id) {
        return commonMapper.mapObject(productService.findById(id), ProductResponse.class);
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest, MultipartFile thumbnail) {
        Product product = commonMapper.mapObject(productRequest, Product.class);
        product.setCategory(categoryService.findById(productRequest.getCategory()));
        return commonMapper.mapObject(productService.updateProduct(product, id, thumbnail), ProductResponse.class);
    }

    public ApiResponse deleteProduct(Long id) {
        productService.deleteProductById(id);
        return new ApiResponse(true, PRODUCT_DELETED);
    }
}

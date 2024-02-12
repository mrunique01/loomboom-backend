package com.loomboom.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.loomboom.contants.ErrorMessage.FILE_NOT_FOUND;
import static com.loomboom.contants.ErrorMessage.SOMETHING_WENT_WRONG;
import static com.loomboom.contants.FileDirectoryConst.PRODUCT_IMAGES;
import static com.loomboom.contants.PathConstants.*;

import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.product.ProductRequest;
import com.loomboom.dto.product.ProductResponse;
import com.loomboom.dto.product.ProductsImageResponse;
import com.loomboom.dto.product.ProductsResponse;
import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.mapper.ProductMapper;
import com.loomboom.model.ProductImage;
import com.loomboom.service.FileUploadService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;
    private final FileUploadService fileUploadService;

    @GetMapping(ALL_PRODUCTS)
    public ResponseEntity<ProductsResponse> getProducts(Pageable pageble) {
        ProductsResponse products = productMapper.getAllProducts(pageble);
        return ResponseEntity.ok(products);

    }

    @GetMapping(PRODUCT_BY_ID)
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId) {
        ProductResponse product = productMapper.findProductById(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping(CREATE_PRODUCT)
    public ResponseEntity<ProductResponse> createProduct(
            @RequestPart("product") @Valid ProductRequest productRequest,
            @RequestPart("thumbnail") MultipartFile thumbnail) {
        ProductResponse productResponse = productMapper.createProduct(productRequest, thumbnail);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping(UPDATE_PRODUCT)
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId,
            @RequestPart("product") @Valid ProductRequest productRequest,
            @RequestPart(name = "thumbnail", required = false) MultipartFile thumbnail) {
        ProductResponse product = productMapper.updateProduct(productId, productRequest, thumbnail);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping(DELETE_PRODUCT)
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        ApiResponse response = productMapper.deleteProduct(productId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = GET_PRODUCT_IMAGE, produces = MediaType.IMAGE_JPEG_VALUE)
    public void getProductImage(@PathVariable String imageName,
            HttpServletResponse response) {
        InputStream resource;
        try {
            resource = fileUploadService.getResourse(PRODUCT_IMAGES, imageName);

            response.setContentType(MediaType.IMAGE_JPEG_VALUE);

            StreamUtils.copy(resource, response.getOutputStream());

        } catch (FileNotFoundException e) {
            throw new ApiRequestException(FILE_NOT_FOUND, HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            throw new ApiRequestException(SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(ADD_PRODUCT_IMAGE)
    public ResponseEntity<ProductsImageResponse> addProductImage(@PathVariable Long productId,
            @RequestPart(name = "productImage") MultipartFile image) {
        ProductsImageResponse productImage = productMapper.addProductImage(productId, image);
        return ResponseEntity.ok(productImage);
    }

    @DeleteMapping(DELETE_PRODUCT_IMAGE)
    public ResponseEntity<ApiResponse> addProductImage(@PathVariable Long imageId) {
        ApiResponse productImage = productMapper.deleteImage(imageId);
        return ResponseEntity.ok(productImage);
    }

}

package com.loomboom.service.impl;

import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.Category;
import com.loomboom.model.Product;
import com.loomboom.model.ProductImage;
import com.loomboom.repository.ProductImageRepository;
import com.loomboom.repository.ProductRepository;
import com.loomboom.service.CategoryService;
import com.loomboom.service.FileUploadService;
import com.loomboom.service.ProductService;
import lombok.RequiredArgsConstructor;
import static com.loomboom.utils.StringUtils.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.loomboom.contants.ErrorMessage.*;
import static com.loomboom.contants.FileDirectoryConst.PRODUCT_IMAGES;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final FileUploadService fileUploadService;

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product createProduct(Product product, MultipartFile thumbnail) {
        if (findByTitle(product.getTitle()) != null) {
            throw new ApiRequestException(DUPLICATE_VALUE, HttpStatus.BAD_REQUEST);
        }
        if (thumbnail.isEmpty()) {
            throw new ApiRequestException(EMPTY_FILE, HttpStatus.BAD_REQUEST);
        }

        // Check if the file is a JPG image
        if (!thumbnail.getContentType().equals("image/jpeg")) {
            throw new ApiRequestException(ONLY_JPG_IMAGES, HttpStatus.BAD_REQUEST);
        }

        try {
            String fileName = fileUploadService.uploadResourse(thumbnail, PRODUCT_IMAGES);
            product.setThumbnail(fileName);
        } catch (IOException e) {
            throw new ApiRequestException(ERROR_ON_UPLOAD, HttpStatus.BAD_REQUEST);

        }
        System.out.println(product.toString() + "jhgf");
        if (product.getCategory() == null) {
            throw new ApiRequestException(INVALID_CATEGORY, HttpStatus.BAD_REQUEST);
        }

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, Long id, MultipartFile thumbnail) {

        Product oldProduct = findById(id);

        if (findById(id) == null) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        Product duplicateProduct = findByTitle(product.getTitle());

        if (duplicateProduct != null && !duplicateProduct.getId().equals(id)) {
            throw new ApiRequestException(DUPLICATE_VALUE, HttpStatus.BAD_REQUEST);
        }
        product.setId(id);

        if (thumbnail == null || thumbnail.isEmpty()) {
            product.setThumbnail(oldProduct.getThumbnail());
        } else {
            if (!thumbnail.isEmpty()) {
                if (!thumbnail.getContentType().equals("image/jpeg")) {
                    throw new ApiRequestException(ONLY_JPG_IMAGES, HttpStatus.BAD_REQUEST);
                }

                try {
                    String fileName = fileUploadService.uploadResourse(thumbnail, PRODUCT_IMAGES);
                    product.setThumbnail(fileName);
                } catch (IOException e) {
                    throw new ApiRequestException(ERROR_ON_UPLOAD, HttpStatus.BAD_REQUEST);

                }
            }
        }
        if (product.getCategory() == null) {
            throw new ApiRequestException(INVALID_CATEGORY, HttpStatus.BAD_REQUEST);
        }
        // Check if the file is a JPG image

        return productRepository.save(product);
    }

    @Override
    public Boolean deleteProductById(Long id) {
        Product product = findById(id);
        if (product == null) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        productRepository.deleteById(id);
        return true;

    }

    @Override
    public Product findByTitle(String title) {
        if (!empty(title)) {
            return productRepository.findByTitle(title).orElse(null);
        }
        return null;
    }

    @Override
    public Product findById(Long id) {
        if (!empty(id)) {
            return productRepository.findById(id).orElse(null);
        }
        return null;
    }

    @Override
    public ProductImage addProductImage(Long id, MultipartFile thumbnail) {
        if (thumbnail.isEmpty()) {
            throw new ApiRequestException(EMPTY_FILE, HttpStatus.BAD_REQUEST);
        }

        // Check if the file is a JPG image
        if (!thumbnail.getContentType().equals("image/jpeg")) {
            throw new ApiRequestException(ONLY_JPG_IMAGES, HttpStatus.BAD_REQUEST);
        }

        try {
            String fileName = fileUploadService.uploadResourse(thumbnail, PRODUCT_IMAGES);
            ProductImage productImage = new ProductImage();
            productImage.setFileName(fileName);
            productImage.setProduct(findById(id));
            productImageRepository.save(productImage);
            return productImage;
        } catch (IOException e) {
            throw new ApiRequestException(ERROR_ON_UPLOAD, HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public Boolean deleteProductImageById(Long id) {
        System.out.println(id+" fdsfds");
        ProductImage productImage = productImageRepository.findById(id).orElse(null);
        if (productImage == null) {
            throw new ApiRequestException(FILE_NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        fileUploadService.deleteResourse(PRODUCT_IMAGES, productImage.getFileName());
        productImageRepository.deleteById(id);
        return true;
    }

}

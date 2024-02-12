package com.loomboom.repository;

import org.springframework.stereotype.Repository;
import com.loomboom.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}

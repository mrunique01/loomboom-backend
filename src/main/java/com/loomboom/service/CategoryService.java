package com.loomboom.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.loomboom.model.Category;

public interface CategoryService {

    List<Category> getAllCategory();

    Page<Category> getAllCategoryByPage(Pageable pageable);

    Category createCategory(Category category);

    Category updateCategory(Category category, Long id);

    Boolean deleteCategoryById(Long id);

    Category findByTitle(String title);

    Category findById(Long id);
}
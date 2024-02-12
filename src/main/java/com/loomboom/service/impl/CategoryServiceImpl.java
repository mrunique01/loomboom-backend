package com.loomboom.service.impl;

import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.Category;
import com.loomboom.repository.CategoryRepository;
import com.loomboom.service.CategoryService;
import lombok.RequiredArgsConstructor;
import static com.loomboom.utils.StringUtils.*;

import java.util.List;

import static com.loomboom.contants.ErrorMessage.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> getAllCategoryByPage(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category createCategory(Category category) {
        if (findByTitle(category.getTitle()) != null) {
            throw new ApiRequestException(DUPLICATE_VALUE, HttpStatus.BAD_REQUEST);
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        if (findById(id) == null) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        Category duplicateCategory = findByTitle(category.getTitle());

        if (duplicateCategory != null && !duplicateCategory.getId().equals(id)) {
            throw new ApiRequestException(DUPLICATE_VALUE, HttpStatus.BAD_REQUEST);
        }
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public Boolean deleteCategoryById(Long id) {
        Category category = findById(id);
        if (category == null) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        categoryRepository.deleteById(id);
        return true;

    }

    @Override
    public Category findByTitle(String title) {
        if (!empty(title)) {
            return categoryRepository.findByTitle(title).orElse(null);
        }
        return null;
    }

    @Override
    public Category findById(Long id) {
        System.out.println(id+" fksjdhfkj");
        if (!empty(id)) {
            return categoryRepository.findById(id).orElse(null);
        }
        return null;
    }

  

}

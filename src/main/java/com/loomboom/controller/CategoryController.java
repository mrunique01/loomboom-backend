package com.loomboom.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static com.loomboom.contants.PathConstants.*;
import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.category.CategoryRequest;
import com.loomboom.dto.category.CategoryResponse;
import com.loomboom.dto.category.CategoriesResponse;
import com.loomboom.mapper.CategoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryMapper categoryMapper;

    @GetMapping(ALL_CATEGORIES)
    public ResponseEntity<CategoriesResponse> getCategories() {
        CategoriesResponse categorys = categoryMapper.getAllCategory();
        return ResponseEntity.ok(categorys);
    }

    @GetMapping(ALL_CATEGORIES_BY_PAGE)
    public ResponseEntity<CategoriesResponse> getCategoriesByPage(Pageable pageble) {
        CategoriesResponse categorys = categoryMapper.getAllCategoryByPage(pageble);
        return ResponseEntity.ok(categorys);
    }

    @GetMapping(CATEGORY_BY_ID)
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long categoryId) {
        CategoryResponse category = categoryMapper.findCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @PostMapping(CREATE_CATEGORY)
    public ResponseEntity<CategoryResponse> createCategory(
            @RequestBody @Valid CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryMapper.createCategory(categoryRequest);
        return ResponseEntity.ok(categoryResponse);
    }

    @PutMapping(UPDATE_CATEGORY)
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long categoryId,
            @RequestBody @Valid CategoryRequest categoryRequest) {

        CategoryResponse category = categoryMapper.updateCategory(categoryId, categoryRequest);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping(DELETE_CATEGORY)
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
        ApiResponse response = categoryMapper.deleteCategory(categoryId);
        return ResponseEntity.ok(response);
    }

}

package com.loomboom.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.pagination.PaginationReponse;
import com.loomboom.dto.category.CategoriesResponse;
import com.loomboom.dto.category.CategoryRequest;
import com.loomboom.dto.category.CategoryResponse;
import com.loomboom.model.Category;
import static com.loomboom.contants.SuccessMessage.*;
import com.loomboom.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final CategoryService categoryService;
    private final CommonMapper commonMapper;

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        Category category = commonMapper.mapObject(categoryRequest, Category.class);
        return commonMapper.mapObject(categoryService.createCategory(category), CategoryResponse.class);
    }

    public CategoriesResponse getAllCategory() {
        List<Category> category = categoryService.getAllCategory();
        List<CategoryResponse> categories = commonMapper.mapListObject(category, CategoryResponse.class);
        CategoriesResponse categoryResponse = new CategoriesResponse();
        categoryResponse.setCategories(categories);
        categoryResponse.setPagination(new PaginationReponse());
        return categoryResponse;
    }

    public CategoriesResponse getAllCategoryByPage(Pageable pageable) {
        Page<Category> category = categoryService.getAllCategoryByPage(pageable);
        List<CategoryResponse> categories = commonMapper.mapListObject(category.getContent(), CategoryResponse.class);
        CategoriesResponse categoryResponse = new CategoriesResponse();
        categoryResponse.setCategories(categories);
        PaginationReponse paginationReponse = commonMapper.paginationData(category);
        categoryResponse.setPagination(paginationReponse);
        return categoryResponse;
    }

    public CategoryResponse findCategoryById(Long id) {
        return commonMapper.mapObject(categoryService.findById(id), CategoryResponse.class);
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = commonMapper.mapObject(categoryRequest, Category.class);
        return commonMapper.mapObject(categoryService.updateCategory(category, id), CategoryResponse.class);
    }

    public ApiResponse deleteCategory(Long id) {
        categoryService.deleteCategoryById(id);
        return new ApiResponse(true, CATEGORY_DELETED);
    }
}

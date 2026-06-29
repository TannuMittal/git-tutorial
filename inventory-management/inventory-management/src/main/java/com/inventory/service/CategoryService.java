package com.inventory.service;

import com.inventory.dto.category.CategoryRequest;
import com.inventory.dto.category.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest request);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategory(Long id);

    CategoryResponse updateCategory(Long id,
                                    CategoryRequest request);

    void deleteCategory(Long id);

}
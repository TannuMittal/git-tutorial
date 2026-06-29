package com.inventory.controller;

import com.inventory.dto.category.CategoryRequest;
import com.inventory.dto.category.CategoryResponse;
import com.inventory.response.ApiResponse;
import com.inventory.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryResponse> createCategory(
            @Valid @RequestBody CategoryRequest request) {

        return ApiResponse.success(
                categoryService.createCategory(request),
                "Category created successfully",
                HttpStatus.CREATED.value()
        );
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> getAllCategories() {

        return ApiResponse.success(
                categoryService.getAllCategories(),
                "Categories fetched successfully",
                HttpStatus.OK.value()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getCategory(
            @PathVariable Long id) {

        return ApiResponse.success(
                categoryService.getCategory(id),
                "Category fetched successfully",
                HttpStatus.OK.value()
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request) {

        return ApiResponse.success(
                categoryService.updateCategory(id, request),
                "Category updated successfully",
                HttpStatus.OK.value()
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        return ApiResponse.success(
                "Deleted Successfully",
                "Category deleted successfully",
                HttpStatus.OK.value()
        );
    }
}
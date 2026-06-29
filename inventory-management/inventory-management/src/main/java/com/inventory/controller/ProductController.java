package com.inventory.controller;

import com.inventory.dto.product.ProductRequest;
import com.inventory.dto.product.ProductResponse;
import com.inventory.response.ApiResponse;
import com.inventory.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest request){

        return ApiResponse.success(
                productService.createProduct(request),
                "Product created successfully",
                HttpStatus.CREATED.value());

    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> getAllProducts(){

        return ApiResponse.success(
                productService.getAllProducts(),
                "Products fetched successfully",
                HttpStatus.OK.value());

    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProduct(
            @PathVariable Long id){

        return ApiResponse.success(
                productService.getProduct(id),
                "Product fetched successfully",
                HttpStatus.OK.value());

    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request){

        return ApiResponse.success(
                productService.updateProduct(id,request),
                "Product updated successfully",
                HttpStatus.OK.value());

    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteProduct(
            @PathVariable Long id){

        productService.deleteProduct(id);

        return ApiResponse.success(
                "Deleted",
                "Product deleted successfully",
                HttpStatus.OK.value());

    }

}
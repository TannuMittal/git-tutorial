package com.inventory.controller;

import com.inventory.dto.supplier.SupplierRequest;
import com.inventory.dto.supplier.SupplierResponse;
import com.inventory.response.ApiResponse;
import com.inventory.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ApiResponse<SupplierResponse> create(@Valid @RequestBody SupplierRequest request) {

        return ApiResponse.success(
                supplierService.createSupplier(request),
                "Supplier created successfully",
                HttpStatus.CREATED.value());
    }

    @GetMapping
    public ApiResponse<List<SupplierResponse>> getAll() {

        return ApiResponse.success(
                supplierService.getAllSuppliers(),
                "Suppliers fetched successfully",
                HttpStatus.OK.value());
    }

    @GetMapping("/{id}")
    public ApiResponse<SupplierResponse> getById(@PathVariable Long id) {

        return ApiResponse.success(
                supplierService.getSupplier(id),
                "Supplier fetched successfully",
                HttpStatus.OK.value());
    }

    @PutMapping("/{id}")
    public ApiResponse<SupplierResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody SupplierRequest request) {

        return ApiResponse.success(
                supplierService.updateSupplier(id, request),
                "Supplier updated successfully",
                HttpStatus.OK.value());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {

        supplierService.deleteSupplier(id);

        return ApiResponse.success(
                "Deleted",
                "Supplier deleted successfully",
                HttpStatus.OK.value());
    }
}
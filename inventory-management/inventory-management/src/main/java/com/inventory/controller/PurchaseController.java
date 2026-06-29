package com.inventory.controller;

import com.inventory.dto.purchase.PurchaseRequest;
import com.inventory.dto.purchase.PurchaseResponse;
import com.inventory.response.ApiResponse;
import com.inventory.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ApiResponse<PurchaseResponse> create(@RequestBody PurchaseRequest request) {

        return ApiResponse.success(
                purchaseService.createPurchase(request),
                "Purchase Created Successfully",
                HttpStatus.CREATED.value());
    }

    @GetMapping
    public ApiResponse<List<PurchaseResponse>> getAll() {

        return ApiResponse.success(
                purchaseService.getAllPurchases(),
                "Purchase List",
                HttpStatus.OK.value());
    }
}
package com.inventory.controller;

import com.inventory.dto.inventory.InventoryRequest;
import com.inventory.dto.inventory.InventoryResponse;
import com.inventory.response.ApiResponse;
import com.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ApiResponse<InventoryResponse> addTransaction(
            @Valid @RequestBody InventoryRequest request){

        return ApiResponse.success(
                inventoryService.addTransaction(request),
                "Inventory Updated Successfully",
                HttpStatus.CREATED.value());

    }

    @GetMapping
    public ApiResponse<List<InventoryResponse>> getTransactions(){

        return ApiResponse.success(
                inventoryService.getTransactions(),
                "Inventory History",
                HttpStatus.OK.value());

    }

}
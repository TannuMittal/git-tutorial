package com.inventory.service;

import com.inventory.dto.inventory.InventoryRequest;
import com.inventory.dto.inventory.InventoryResponse;

import java.util.List;

public interface InventoryService {

    InventoryResponse addTransaction(
            InventoryRequest request);

    List<InventoryResponse> getTransactions();

}
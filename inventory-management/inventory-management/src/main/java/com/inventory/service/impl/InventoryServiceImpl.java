package com.inventory.service.impl;

import com.inventory.dto.inventory.InventoryRequest;
import com.inventory.dto.inventory.InventoryResponse;
import com.inventory.entity.InventoryTransaction;
import com.inventory.entity.Product;
import com.inventory.enums.TransactionType;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.repository.InventoryTransactionRepository;
import com.inventory.repository.ProductRepository;
import com.inventory.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final ProductRepository productRepository;
    private final InventoryTransactionRepository inventoryRepository;

    @Override
    @Transactional
    public InventoryResponse addTransaction(InventoryRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));

        if (request.getTransactionType() == TransactionType.IN) {

            product.setQuantity(
                    product.getQuantity() + request.getQuantity());

        } else {

            if (product.getQuantity() < request.getQuantity()) {
                throw new IllegalArgumentException(
                        "Insufficient Stock");
            }

            product.setQuantity(
                    product.getQuantity() - request.getQuantity());

        }

        productRepository.save(product);

        InventoryTransaction transaction =
                InventoryTransaction.builder()
                        .product(product)
                        .transactionType(request.getTransactionType())
                        .quantity(request.getQuantity())
                        .remarks(request.getRemarks())
                        .build();

        InventoryTransaction saved =
                inventoryRepository.save(transaction);

        return map(saved);

    }

    @Override
    public List<InventoryResponse> getTransactions() {

        return inventoryRepository.findAll()
                .stream()
                .map(this::map)
                .toList();

    }

    private InventoryResponse map(
            InventoryTransaction transaction){

        return InventoryResponse.builder()
                .id(transaction.getId())
                .productName(transaction.getProduct().getName())
                .transactionType(transaction.getTransactionType())
                .quantity(transaction.getQuantity())
                .remarks(transaction.getRemarks())
                .build();

    }

}
package com.inventory.service.impl;

import com.inventory.dto.purchase.PurchaseItemRequest;
import com.inventory.dto.purchase.PurchaseRequest;
import com.inventory.dto.purchase.PurchaseResponse;
import com.inventory.entity.*;
import com.inventory.enums.PurchaseStatus;
import com.inventory.enums.TransactionType;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.repository.*;
import com.inventory.service.PurchaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final InventoryTransactionRepository inventoryRepository;

    @Override
    @Transactional
    public PurchaseResponse createPurchase(PurchaseRequest request) {

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .supplier(supplier)
                .purchaseDate(LocalDate.now())
                .status(PurchaseStatus.COMPLETED)
                .build();

        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);

        BigDecimal total = BigDecimal.ZERO;
        List<PurchaseItem> items = new ArrayList<>();

        for (PurchaseItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            product.setQuantity(product.getQuantity() + itemRequest.getQuantity());
            productRepository.save(product);

            InventoryTransaction transaction = InventoryTransaction.builder()
                    .product(product)
                    .transactionType(TransactionType.IN)
                    .quantity(itemRequest.getQuantity())
                    .remarks("Purchase Order #" + purchaseOrder.getId())
                    .build();

            inventoryRepository.save(transaction);

            BigDecimal lineTotal = itemRequest.getUnitPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            PurchaseItem purchaseItem = PurchaseItem.builder()
                    .purchaseOrder(purchaseOrder)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .unitPrice(itemRequest.getUnitPrice())
                    .totalPrice(lineTotal)
                    .build();

            items.add(purchaseItem);

            total = total.add(lineTotal);
        }

        purchaseItemRepository.saveAll(items);

        purchaseOrder.setItems(items);
        purchaseOrder.setTotalAmount(total);

        purchaseOrderRepository.save(purchaseOrder);

        return PurchaseResponse.builder()
                .id(purchaseOrder.getId())
                .supplier(supplier.getName())
                .purchaseDate(purchaseOrder.getPurchaseDate())
                .totalAmount(total)
                .build();
    }

    @Override
    public List<PurchaseResponse> getAllPurchases() {

        return purchaseOrderRepository.findAll()
                .stream()
                .map(order -> PurchaseResponse.builder()
                        .id(order.getId())
                        .supplier(order.getSupplier().getName())
                        .purchaseDate(order.getPurchaseDate())
                        .totalAmount(order.getTotalAmount())
                        .build())
                .toList();
    }
}
package com.inventory.dto.inventory;

import com.inventory.enums.TransactionType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InventoryResponse {

    private Long id;

    private String productName;

    private TransactionType transactionType;

    private Integer quantity;

    private String remarks;

}
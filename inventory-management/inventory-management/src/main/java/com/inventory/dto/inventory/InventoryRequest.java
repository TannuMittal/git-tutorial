package com.inventory.dto.inventory;

import com.inventory.enums.TransactionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequest {

    @NotNull
    private Long productId;

    @NotNull
    private TransactionType transactionType;

    @NotNull
    @Min(1)
    private Integer quantity;

    private String remarks;

}
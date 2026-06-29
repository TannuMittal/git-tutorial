package com.inventory.dto.purchase;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PurchaseItemRequest {

    private Long productId;

    private Integer quantity;

    private BigDecimal unitPrice;

}
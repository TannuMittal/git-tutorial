package com.inventory.dto.purchase;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class PurchaseResponse {

    private Long id;

    private String supplier;

    private LocalDate purchaseDate;

    private BigDecimal totalAmount;

}
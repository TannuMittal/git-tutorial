package com.inventory.dto.product;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private String sku;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    private Boolean active;

    private String categoryName;

}
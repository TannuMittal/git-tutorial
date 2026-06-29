package com.inventory.dto.purchase;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PurchaseRequest {

    private Long supplierId;

    private List<PurchaseItemRequest> items;

}

package com.inventory.service;

import com.inventory.dto.purchase.PurchaseRequest;
import com.inventory.dto.purchase.PurchaseResponse;

import java.util.List;

public interface PurchaseService {

    PurchaseResponse createPurchase(PurchaseRequest request);

    List<PurchaseResponse> getAllPurchases();

}
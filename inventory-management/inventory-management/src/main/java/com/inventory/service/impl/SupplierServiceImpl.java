package com.inventory.service.impl;

import com.inventory.dto.supplier.SupplierRequest;
import com.inventory.dto.supplier.SupplierResponse;
import com.inventory.entity.Supplier;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.repository.SupplierRepository;
import com.inventory.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public SupplierResponse createSupplier(SupplierRequest request) {

        if (supplierRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Supplier already exists");
        }

        Supplier supplier = Supplier.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .build();

        return map(supplierRepository.save(supplier));
    }

    @Override
    public List<SupplierResponse> getAllSuppliers() {

        return supplierRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public SupplierResponse getSupplier(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Supplier not found"));

        return map(supplier);
    }

    @Override
    public SupplierResponse updateSupplier(Long id, SupplierRequest request) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Supplier not found"));

        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setPhone(request.getPhone());
        supplier.setAddress(request.getAddress());

        return map(supplierRepository.save(supplier));
    }

    @Override
    public void deleteSupplier(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Supplier not found"));

        supplier.setActive(false);

        supplierRepository.save(supplier);
    }

    private SupplierResponse map(Supplier supplier) {

        return SupplierResponse.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .email(supplier.getEmail())
                .phone(supplier.getPhone())
                .address(supplier.getAddress())
                .active(supplier.getActive())
                .build();
    }
}
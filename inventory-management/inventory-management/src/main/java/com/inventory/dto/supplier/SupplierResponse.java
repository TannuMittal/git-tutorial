package com.inventory.dto.supplier;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SupplierResponse {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String address;

    private Boolean active;

}
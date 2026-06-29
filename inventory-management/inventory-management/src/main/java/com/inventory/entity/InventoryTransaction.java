package com.inventory.entity;

import com.inventory.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="inventory_transactions")
public class InventoryTransaction extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Integer quantity;

    private String remarks;

}
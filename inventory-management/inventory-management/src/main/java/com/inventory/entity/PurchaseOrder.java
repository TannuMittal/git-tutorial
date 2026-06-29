package com.inventory.entity;

import com.inventory.enums.PurchaseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="purchase_orders")
public class PurchaseOrder extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    private LocalDate purchaseDate;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    @OneToMany(mappedBy = "purchaseOrder",
            cascade = CascadeType.ALL)
    private List<PurchaseItem> items;

}
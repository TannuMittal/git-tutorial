package com.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false,unique=true)
    private String sku;

    private String description;

    @Column(nullable=false)
    private BigDecimal price;

    @Column(nullable=false)
    private Integer quantity;

    @Builder.Default
    private Boolean active=true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id",nullable=false)
    private Category category;

}
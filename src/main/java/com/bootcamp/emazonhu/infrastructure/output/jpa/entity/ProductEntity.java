package com.bootcamp.emazonhu.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull
    @Column(name = "product_name", length = 50, nullable = false)
    private String productName;

    @NotNull
    @Column(name = "product_description", length = 120, nullable = false)
    private String productDescription;

    @NotNull
    @Column(name = "product_quantity", nullable = false)
    private Long productQuantity;

    @NotNull
    @Column(name = "product_price", nullable = false)
    private Long productPrice;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity productBrand;
}

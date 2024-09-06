package com.bootcamp.emazonhu.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brand")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;

    @NotNull
    @Column(name = "brand_name", length = 50, nullable = false)
    private String brandName;

    @NotNull
    @Column(name = "brand_description", length = 120, nullable = false)
    private String brandDescription;
}

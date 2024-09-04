package com.bootcamp.emazonhu.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @NotNull
    @Column(name = "category_name", length = 50, nullable = false)
    private String categoryName;

    @NotNull
    @Column(name = "category_description", length = 90, nullable = false)
    private String categoryDescription;
}

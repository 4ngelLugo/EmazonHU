package com.bootcamp.emazonhu.infrastructure.output.jpa.repository;

import com.bootcamp.emazonhu.infrastructure.output.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByProductId(Long productId);

    Optional<ProductEntity> findByProductName(String productName);
}

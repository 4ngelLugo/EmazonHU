package com.bootcamp.emazonhu.infrastructure.output.jpa.repository;

import com.bootcamp.emazonhu.infrastructure.output.jpa.entity.BrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

    Optional<BrandEntity> findByBrandId(Long brandId);

    Optional<BrandEntity> findByBrandName(String brandName);

    @NonNull
    Page<BrandEntity> findAll(@NonNull Pageable pageable);
}

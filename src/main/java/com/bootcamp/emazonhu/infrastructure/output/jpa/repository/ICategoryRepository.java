package com.bootcamp.emazonhu.infrastructure.output.jpa.repository;

import com.bootcamp.emazonhu.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByCategoryId(Long categoryId);

    Optional<CategoryEntity> findByCategoryName(String categoryName);

    @NonNull
    Page<CategoryEntity> findAll(@NonNull Pageable pageable);

}

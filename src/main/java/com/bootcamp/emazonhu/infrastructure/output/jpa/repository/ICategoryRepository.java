package com.bootcamp.emazonhu.infrastructure.output.jpa.repository;

import com.bootcamp.emazonhu.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByCategoryId(Long categoryId);

    Optional<CategoryEntity> findByCategoryName(String categoryName);

}

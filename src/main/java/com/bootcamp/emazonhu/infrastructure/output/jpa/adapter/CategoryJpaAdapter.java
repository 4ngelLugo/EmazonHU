package com.bootcamp.emazonhu.infrastructure.output.jpa.adapter;

import com.bootcamp.emazonhu.domain.model.Category;
import com.bootcamp.emazonhu.domain.spi.ICategoryPersistencePort;
import com.bootcamp.emazonhu.infrastructure.exception.NoDataFoundException;
import com.bootcamp.emazonhu.infrastructure.exception.category.CategoryAlreadyExistException;
import com.bootcamp.emazonhu.infrastructure.exception.category.CategoryNotFoundException;
import com.bootcamp.emazonhu.infrastructure.output.jpa.entity.CategoryEntity;
import com.bootcamp.emazonhu.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.bootcamp.emazonhu.infrastructure.output.jpa.repository.ICategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    public CategoryJpaAdapter(ICategoryRepository categoryRepository, CategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }

    @Override
    public void saveCategory(Category category) {
        if (category.getCategoryName() != null && categoryRepository.findByCategoryName(category.getCategoryName()).isPresent()) {
            throw new CategoryAlreadyExistException();
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        if (categoryEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }

    @Override
    public Category getCategoryId(Long categoryId) {
        return categoryEntityMapper.toCategory(categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(CategoryNotFoundException::new));
    }

    @Override
    public Category getCategoryName(String categoryName) {
        return categoryEntityMapper.toCategory(categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(CategoryNotFoundException::new));
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(categoryEntity);
    }
}

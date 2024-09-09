package com.bootcamp.emazonhu.infrastructure.output.jpa.adapter;

import com.bootcamp.emazonhu.domain.model.Category;
import com.bootcamp.emazonhu.domain.spi.ICategoryPersistencePort;
import com.bootcamp.emazonhu.infrastructure.exception.NoDataFoundException;
import com.bootcamp.emazonhu.infrastructure.exception.category.CategoryAlreadyExistException;
import com.bootcamp.emazonhu.infrastructure.exception.category.CategoryNotFoundException;
import com.bootcamp.emazonhu.infrastructure.output.jpa.entity.CategoryEntity;
import com.bootcamp.emazonhu.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.bootcamp.emazonhu.infrastructure.output.jpa.repository.ICategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


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
    public List<Category> getAllCategories(Integer page, Integer size, String sortBy, Boolean asc) {
        Sort sort = Boolean.TRUE.equals(asc) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(pageable);

        if (categoryEntityPage.isEmpty()) {
            throw new NoDataFoundException();
        }

        return categoryEntityMapper.toCategoryList(categoryEntityPage.getContent());
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

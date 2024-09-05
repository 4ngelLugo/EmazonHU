package com.bootcamp.emazonhu.domain.usecase;

import com.bootcamp.emazonhu.domain.api.ICategoryServicePort;
import com.bootcamp.emazonhu.domain.model.Category;
import com.bootcamp.emazonhu.domain.spi.ICategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryPersistencePort.getAllCategories();
    }

    @Override
    public Category getCategoryId(Long categoryId) {
        return categoryPersistencePort.getCategoryId(categoryId);
    }

    @Override
    public Category getCategoryName(String categoryName) {
        return categoryPersistencePort.getCategoryName(categoryName);
    }

    @Override
    public void updateCategory(Category category) {
        categoryPersistencePort.updateCategory(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryPersistencePort.deleteCategory(categoryId);
    }
}

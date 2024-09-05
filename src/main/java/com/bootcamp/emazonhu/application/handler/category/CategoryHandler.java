package com.bootcamp.emazonhu.application.handler.category;

import com.bootcamp.emazonhu.application.dto.category.CategoryRequest;
import com.bootcamp.emazonhu.application.dto.category.CategoryResponse;
import com.bootcamp.emazonhu.application.mapper.category.CategoryRequestMapper;
import com.bootcamp.emazonhu.application.mapper.category.CategoryResponseMapper;
import com.bootcamp.emazonhu.domain.api.ICategoryServicePort;
import com.bootcamp.emazonhu.domain.model.Category;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler{

    private final ICategoryServicePort categoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;

    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryResponseMapper.toResonseList(categoryServicePort.getAllCategories());
    }

    @Override
    public CategoryResponse getCategoryName(String categoryName) {
        Category category = categoryServicePort.getCategoryName(categoryName);
        return categoryResponseMapper.toResponse(category);
    }

    @Override
    public CategoryResponse getCategoryId(Long categoryId) {
        Category category = categoryServicePort.getCategoryId(categoryId);
        return categoryResponseMapper.toResponse(category);
    }

    @Override
    public void updateCategory(CategoryRequest categoryRequest) {
        Category oldCategory = categoryServicePort.getCategoryId(categoryRequest.getCategoryId());
        Category newCategory = categoryRequestMapper.toCategory(categoryRequest);

        newCategory.setCategoryId(oldCategory.getCategoryId());

        categoryServicePort.updateCategory(newCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryServicePort.deleteCategory(categoryId);
    }
}

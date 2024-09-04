package com.bootcamp.emazonhu.application.handler.category;

import com.bootcamp.emazonhu.application.dto.category.CategoryRequest;
import com.bootcamp.emazonhu.application.dto.category.CategoryResponse;

import java.util.List;

public interface ICategoryHandler {

    void saveCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryName(String categoryName);

    CategoryResponse getCategoryId(Long categoryId);

    void updateCategory(CategoryRequest categoryRequest);

    void deleteCategory(Long categoryId);
}

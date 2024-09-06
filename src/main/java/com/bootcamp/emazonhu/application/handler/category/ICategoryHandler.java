package com.bootcamp.emazonhu.application.handler.category;

import com.bootcamp.emazonhu.application.dto.category.CategoryRequest;
import com.bootcamp.emazonhu.application.dto.category.CategoryResponse;
import com.bootcamp.emazonhu.domain.model.Category;

import java.util.List;

public interface ICategoryHandler {

    void saveCategory(CategoryRequest categoryRequest);

    List<Category> getAllCategories(Integer page, Integer size, String sortBy, Boolean asc);

    CategoryResponse getCategoryName(String categoryName);

    CategoryResponse getCategoryId(Long categoryId);

    void updateCategory(CategoryRequest categoryRequest);

    void deleteCategory(Long categoryId);
}

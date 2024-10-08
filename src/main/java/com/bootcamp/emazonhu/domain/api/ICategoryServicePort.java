package com.bootcamp.emazonhu.domain.api;

import com.bootcamp.emazonhu.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {

    void saveCategory(Category category);

    List<Category> getAllCategories(Integer page, Integer size, String sortBy, Boolean asc);

    Category getCategoryId(Long categoryId);

    Category getCategoryName(String categoryName);

    void updateCategory(Category category);

    void deleteCategory(Long categoryId);
}

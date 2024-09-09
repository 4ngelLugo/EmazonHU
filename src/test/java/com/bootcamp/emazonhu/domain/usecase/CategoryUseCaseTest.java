package com.bootcamp.emazonhu.domain.usecase;

import com.bootcamp.emazonhu.domain.model.Category;
import com.bootcamp.emazonhu.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory_shouldInvokePersistencePort() {
        Category category = new Category(1L, "CategoryName", "Description");

        categoryUseCase.saveCategory(category);

        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void getAllCategories_shouldReturnCategoryList() {
        Category category1 = new Category(1L, "Category 1", "Description 1");
        Category category2 = new Category(2L, "Category 2", "Description 2");
        List<Category> expectedCategories = Arrays.asList(category1, category2);
        Integer page = 0;
        Integer size = 10;
        String sortBy = "name";
        Boolean asc = true;

        when(categoryPersistencePort.getAllCategories(page, size, sortBy, asc)).thenReturn(expectedCategories);

        List<Category> actualCategories = categoryUseCase.getAllCategories(page, size, sortBy, asc);

        assertEquals(expectedCategories, actualCategories);
        verify(categoryPersistencePort, times(1)).getAllCategories(page, size, sortBy, asc);
    }

    @Test
    void getCategoryById_shouldReturnCategory() {
        Long categoryId = 1L;
        Category expectedCategory = new Category(categoryId, "Category 1", "Description 1");
        when(categoryPersistencePort.getCategoryId(categoryId)).thenReturn(expectedCategory);

        Category actualCategory = categoryUseCase.getCategoryId(categoryId);

        assertEquals(expectedCategory, actualCategory);
        verify(categoryPersistencePort, times(1)).getCategoryId(categoryId);
    }

    @Test
    void getCategoryByName_shouldReturnCategory() {
        String categoryName = "Category 1";
        Category expectedCategory = new Category(1L, categoryName, "Description 1");
        when(categoryPersistencePort.getCategoryName(categoryName)).thenReturn(expectedCategory);

        Category actualCategory = categoryUseCase.getCategoryName(categoryName);

        assertEquals(expectedCategory, actualCategory);
        verify(categoryPersistencePort, times(1)).getCategoryName(categoryName);
    }

    @Test
    void updateCategory_shouldInvokePersistencePort() {
        Category category = new Category(1L, "CategoryName", "Description");

        categoryUseCase.updateCategory(category);

        verify(categoryPersistencePort, times(1)).updateCategory(category);
    }

    @Test
    void deleteCategory_shouldInvokePersistencePort() {
        Long categoryId = 1L;

        categoryUseCase.deleteCategory(categoryId);

        verify(categoryPersistencePort, times(1)).deleteCategory(categoryId);
    }
}

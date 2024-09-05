package com.bootcamp.emazonhu.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Electronics");
        category.setCategoryDescription("All electronic devices");
    }

    @AfterEach
    void tearDown() {
        category = null;
    }

    @Test
    void getCategoryId() {
        assertEquals(1L, category.getCategoryId());
    }

    @Test
    void setCategoryId() {
        category.setCategoryId(2L);
        assertEquals(2L, category.getCategoryId());
    }

    @Test
    void getCategoryName() {
        assertEquals("Electronics", category.getCategoryName());
    }

    @Test
    void setCategoryName() {
        category.setCategoryName("Books");
        assertEquals("Books", category.getCategoryName());
    }

    @Test
    void getCategoryDescription() {
        assertEquals("All electronic devices", category.getCategoryDescription());
    }

    @Test
    void setCategoryDescription() {
        category.setCategoryDescription("Books and literature");
        assertEquals("Books and literature", category.getCategoryDescription());
    }
}

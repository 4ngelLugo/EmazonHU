package com.bootcamp.emazonhu.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {

    private Brand brand;

    @BeforeEach
    void setUp() {
        brand = new Brand();
        brand.setBrandId(1L);
        brand.setBrandName("Test Brand");
        brand.setBrandDescription("Test Description");
    }

    @AfterEach
    void tearDown() {
        brand = null;
    }

    @Test
    void getBrandId() {
        assertEquals(1L, brand.getBrandId());
    }

    @Test
    void setBrandId() {
        brand.setBrandId(2L);
        assertEquals(2L, brand.getBrandId());
    }

    @Test
    void getBrandName() {
        assertEquals("Test Brand", brand.getBrandName());
    }

    @Test
    void setBrandName() {
        brand.setBrandName("New Brand");
        assertEquals("New Brand", brand.getBrandName());
    }

    @Test
    void getBrandDescription() {
        assertEquals("Test Description", brand.getBrandDescription());
    }

    @Test
    void setBrandDescription() {
        brand.setBrandDescription("New Description");
        assertEquals("New Description", brand.getBrandDescription());
    }
}

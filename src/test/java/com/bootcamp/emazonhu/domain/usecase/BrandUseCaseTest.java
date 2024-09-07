package com.bootcamp.emazonhu.domain.usecase;

import com.bootcamp.emazonhu.domain.model.Brand;
import com.bootcamp.emazonhu.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBrand() {
        Brand brand = new Brand(1L, "Brand1", "Description1");

        brandUseCase.saveBrand(brand);

        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }

    @Test
    void getAllBrands() {
        Brand brand1 = new Brand(1L, "Brand1", "Description1");
        Brand brand2 = new Brand(2L, "Brand2", "Description2");
        List<Brand> expectedBrands = Arrays.asList(brand1, brand2);

        when(brandPersistencePort.getAllBrands()).thenReturn(expectedBrands);

        List<Brand> actualBrands = brandUseCase.getAllBrands();

        assertEquals(expectedBrands, actualBrands);
        verify(brandPersistencePort, times(1)).getAllBrands();
    }

    @Test
    void getBrandById() {
        Long brandId = 1L;
        Brand expectedBrand = new Brand(brandId, "Brand1", "Description1");

        when(brandPersistencePort.getBrandById(brandId)).thenReturn(expectedBrand);

        Brand actualBrand = brandUseCase.getBrandById(brandId);

        assertEquals(expectedBrand, actualBrand);
        verify(brandPersistencePort, times(1)).getBrandById(brandId);
    }

    @Test
    void getBrandByName() {
        String brandName = "Brand1";
        Brand expectedBrand = new Brand(1L, brandName, "Description1");

        when(brandPersistencePort.getBrandByName(brandName)).thenReturn(expectedBrand);

        Brand actualBrand = brandUseCase.getBrandByName(brandName);

        assertEquals(expectedBrand, actualBrand);
        verify(brandPersistencePort, times(1)).getBrandByName(brandName);
    }

    @Test
    void updateBrand() {
        Brand brand = new Brand(1L, "Brand1", "Updated Description");

        brandUseCase.updateBrand(brand);

        verify(brandPersistencePort, times(1)).updateBrand(brand);
    }

    @Test
    void deleteBrand() {
        Long brandId = 1L;

        brandUseCase.deleteBrand(brandId);

        verify(brandPersistencePort, times(1)).deleteBrand(brandId);
    }
}

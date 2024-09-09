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
    void saveBrand_shouldInvokePersistencePort() {
        Brand brand = new Brand(1L, "BrandName", "Description");

        brandUseCase.saveBrand(brand);

        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }

    @Test
    void getAllBrands_shouldReturnBrandList() {
        Brand brand1 = new Brand(1L, "Brand 1", "Description 1");
        Brand brand2 = new Brand(2L, "Brand 2", "Description 2");
        List<Brand> expectedBrands = Arrays.asList(brand1, brand2);
        Integer page = 0;
        Integer size = 10;
        String sortBy = "name";
        Boolean asc = true;

        when(brandPersistencePort.getAllBrands(page, size, sortBy, asc)).thenReturn(expectedBrands);

        List<Brand> actualBrands = brandUseCase.getAllBrands(page, size, sortBy, asc);

        assertEquals(expectedBrands, actualBrands);
        verify(brandPersistencePort, times(1)).getAllBrands(page, size, sortBy, asc);
    }

    @Test
    void getBrandById_shouldReturnBrand() {
        Long brandId = 1L;
        Brand expectedBrand = new Brand(brandId, "Brand 1", "Description 1");
        when(brandPersistencePort.getBrandById(brandId)).thenReturn(expectedBrand);

        Brand actualBrand = brandUseCase.getBrandById(brandId);

        assertEquals(expectedBrand, actualBrand);
        verify(brandPersistencePort, times(1)).getBrandById(brandId);
    }

    @Test
    void getBrandByName_shouldReturnBrand() {
        String brandName = "Brand 1";
        Brand expectedBrand = new Brand(1L, brandName, "Description 1");
        when(brandPersistencePort.getBrandByName(brandName)).thenReturn(expectedBrand);

        Brand actualBrand = brandUseCase.getBrandByName(brandName);

        assertEquals(expectedBrand, actualBrand);
        verify(brandPersistencePort, times(1)).getBrandByName(brandName);
    }

    @Test
    void updateBrand_shouldInvokePersistencePort() {
        Brand brand = new Brand(1L, "BrandName", "Description");

        brandUseCase.updateBrand(brand);

        verify(brandPersistencePort, times(1)).updateBrand(brand);
    }

    @Test
    void deleteBrand_shouldInvokePersistencePort() {
        Long brandId = 1L;

        brandUseCase.deleteBrand(brandId);

        verify(brandPersistencePort, times(1)).deleteBrand(brandId);
    }
}

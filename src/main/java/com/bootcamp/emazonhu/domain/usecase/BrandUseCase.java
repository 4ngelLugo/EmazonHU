package com.bootcamp.emazonhu.domain.usecase;

import com.bootcamp.emazonhu.domain.api.IBrandServicePort;
import com.bootcamp.emazonhu.domain.model.Brand;
import com.bootcamp.emazonhu.domain.spi.IBrandPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandUseCase implements IBrandServicePort {

    public final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        brandPersistencePort.saveBrand(brand);
    }

    @Override
    public List<Brand> getAllBrands(Integer page, Integer size, String sortBy, Boolean asc) {
        return brandPersistencePort.getAllBrands(page, size, sortBy, asc);
    }

    @Override
    public Brand getBrandById(Long brandId) {
        return brandPersistencePort.getBrandById(brandId);
    }

    @Override
    public Brand getBrandByName(String brandName) {
        return brandPersistencePort.getBrandByName(brandName);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandPersistencePort.updateBrand(brand);
    }

    @Override
    public void deleteBrand(Long brandId) {
        brandPersistencePort.deleteBrand(brandId);
    }
}

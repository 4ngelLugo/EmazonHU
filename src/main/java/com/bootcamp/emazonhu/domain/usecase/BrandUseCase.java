package com.bootcamp.emazonhu.domain.usecase;

import com.bootcamp.emazonhu.domain.api.IBrandServicePort;
import com.bootcamp.emazonhu.domain.model.Brand;
import com.bootcamp.emazonhu.domain.spi.IBrandPersistencePort;

import java.util.List;

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
    public List<Brand> getAllBrands() {
        return brandPersistencePort.getAllBrands();
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

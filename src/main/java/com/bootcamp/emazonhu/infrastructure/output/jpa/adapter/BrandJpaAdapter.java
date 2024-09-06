package com.bootcamp.emazonhu.infrastructure.output.jpa.adapter;

import com.bootcamp.emazonhu.domain.model.Brand;
import com.bootcamp.emazonhu.domain.spi.IBrandPersistencePort;
import com.bootcamp.emazonhu.infrastructure.exception.NoDataFoundException;
import com.bootcamp.emazonhu.infrastructure.exception.brand.BrandAlreadyExistException;
import com.bootcamp.emazonhu.infrastructure.exception.brand.BrandNotFoundException;
import com.bootcamp.emazonhu.infrastructure.output.jpa.entity.BrandEntity;
import com.bootcamp.emazonhu.infrastructure.output.jpa.mapper.BrandEntityMapper;
import com.bootcamp.emazonhu.infrastructure.output.jpa.repository.IBrandRepository;

import java.util.List;

public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    public BrandJpaAdapter(IBrandRepository brandRepository, BrandEntityMapper brandEntityMapper) {
        this.brandRepository = brandRepository;
        this.brandEntityMapper = brandEntityMapper;
    }

    @Override
    public void saveBrand(Brand brand) {
        if (brand.getBrandName() != null && brandRepository.findByBrandName(brand.getBrandName()).isPresent()) {
            throw new BrandAlreadyExistException();
        }
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public List<Brand> getAllBrands() {
        List<BrandEntity> brandEntityList = brandRepository.findAll();

        if (brandEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }

        return brandEntityMapper.toBrandList(brandEntityList);
    }

    @Override
    public Brand getBrandById(Long brandId) {
        return brandEntityMapper.toBrand(brandRepository.findByBrandId(brandId)
                .orElseThrow(BrandNotFoundException::new));
    }

    @Override
    public Brand getBrandByName(String brandName) {
        return brandEntityMapper.toBrand(brandRepository.findByBrandName(brandName)
                .orElseThrow(BrandNotFoundException::new));
    }

    @Override
    public void updateBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public void deleteBrand(Long brandId) {
        BrandEntity brandEntity = brandRepository.findByBrandId(brandId)
                .orElseThrow(BrandNotFoundException::new);
        brandRepository.delete(brandEntity);
    }
}

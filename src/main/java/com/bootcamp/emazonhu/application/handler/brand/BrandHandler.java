package com.bootcamp.emazonhu.application.handler.brand;

import com.bootcamp.emazonhu.application.dto.brand.BrandRequest;
import com.bootcamp.emazonhu.application.dto.brand.BrandResponse;
import com.bootcamp.emazonhu.application.mapper.brand.BrandRequestMapper;
import com.bootcamp.emazonhu.application.mapper.brand.BrandResponseMapper;
import com.bootcamp.emazonhu.domain.api.IBrandServicePort;
import com.bootcamp.emazonhu.domain.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler{

    private final IBrandServicePort brandServicePort;
    private final BrandRequestMapper brandRequestMapper;
    private final BrandResponseMapper brandResponseMapper;

    @Override
    public void saveBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.saveBrand(brand);
    }

    @Override
    public List<BrandResponse> getAllBrands() {
        return brandResponseMapper.toResponseList(brandServicePort.getAllBrands());
    }

    @Override
    public BrandResponse getBrandName(String brandName) {
        Brand brand = brandServicePort.getBrandByName(brandName);
        return brandResponseMapper.toResponse(brand);
    }

    @Override
    public BrandResponse getBrandId(Long brandId) {
        Brand brand = brandServicePort.getBrandById(brandId);
        return brandResponseMapper.toResponse(brand);
    }

    @Override
    public void updateBrand(BrandRequest brandRequest) {
        Brand oldBrand = brandServicePort.getBrandById(brandRequest.getBrandId());
        Brand newBrand = brandRequestMapper.toBrand(brandRequest);

        newBrand.setBrandId(oldBrand.getBrandId());

        brandServicePort.updateBrand(newBrand);
    }

    @Override
    public void deleteBrand(Long brandId) {
        brandServicePort.deleteBrand(brandId);
    }
}

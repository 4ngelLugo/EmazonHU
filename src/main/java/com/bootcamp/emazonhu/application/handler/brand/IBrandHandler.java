package com.bootcamp.emazonhu.application.handler.brand;

import com.bootcamp.emazonhu.application.dto.brand.BrandRequest;
import com.bootcamp.emazonhu.application.dto.brand.BrandResponse;

import java.util.List;

public interface IBrandHandler {

    void saveBrand(BrandRequest brandRequest);

    List<BrandResponse> getAllBrands();

    BrandResponse getBrandName(String brandName);

    BrandResponse getBrandId(Long brandId);

    void updateBrand(BrandRequest brandRequest);

    void deleteBrand(Long brandId);
}

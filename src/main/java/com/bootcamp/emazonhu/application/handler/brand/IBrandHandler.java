package com.bootcamp.emazonhu.application.handler.brand;

import com.bootcamp.emazonhu.application.dto.brand.BrandRequest;
import com.bootcamp.emazonhu.application.dto.brand.BrandResponse;
import com.bootcamp.emazonhu.domain.model.Brand;

import java.util.List;

public interface IBrandHandler {

    void saveBrand(BrandRequest brandRequest);

    List<Brand> getAllBrands(Integer page, Integer size, String sortBy, Boolean asc);

    BrandResponse getBrandName(String brandName);

    BrandResponse getBrandId(Long brandId);

    void updateBrand(BrandRequest brandRequest);

    void deleteBrand(Long brandId);
}

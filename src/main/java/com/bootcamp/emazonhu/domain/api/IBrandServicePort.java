package com.bootcamp.emazonhu.domain.api;

import com.bootcamp.emazonhu.domain.model.Brand;

import java.util.List;

public interface IBrandServicePort {

    void saveBrand(Brand brand);

    List<Brand> getAllBrands();

    Brand getBrandById(Long brandId);

    Brand getBrandByName(String brandName);

    void updateBrand(Brand brand);

    void deleteBrand(Long brandId);
}

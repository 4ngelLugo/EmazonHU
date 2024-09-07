package com.bootcamp.emazonhu.application.mapper.brand;

import com.bootcamp.emazonhu.application.dto.brand.BrandResponse;
import com.bootcamp.emazonhu.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandResponseMapper {

    BrandResponse toResponse(Brand brand);

    default List<BrandResponse> toResponseList(List<Brand> brandList) {
        return brandList.stream()
                .map(brand -> {
                    BrandResponse brandResponse = new BrandResponse();
                    brandResponse.setBrandId(brand.getBrandId());
                    brandResponse.setBrandName(brand.getBrandName());
                    brandResponse.setBrandDescription(brand.getBrandDescription());
                    return brandResponse;
                }).toList();
    }
}

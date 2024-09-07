package com.bootcamp.emazonhu.application.mapper.brand;

import com.bootcamp.emazonhu.application.dto.brand.BrandRequest;
import com.bootcamp.emazonhu.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandRequestMapper {

    Brand toBrand(BrandRequest brandRequest);
}

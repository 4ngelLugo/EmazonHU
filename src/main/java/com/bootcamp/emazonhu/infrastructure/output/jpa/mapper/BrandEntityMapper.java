package com.bootcamp.emazonhu.infrastructure.output.jpa.mapper;

import com.bootcamp.emazonhu.domain.model.Brand;
import com.bootcamp.emazonhu.infrastructure.output.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandEntityMapper {

    BrandEntity toEntity(Brand brand);

    Brand toBrand(BrandEntity brandEntity);

    List<Brand> toBrandList(List<BrandEntity> brandEntityList);
}

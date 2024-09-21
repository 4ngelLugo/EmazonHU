package com.bootcamp.emazonhu.application.mapper.product;

import com.bootcamp.emazonhu.application.dto.product.ProductRequest;
import com.bootcamp.emazonhu.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductRequestMapper {

    Product toProduct(ProductRequest productRequest);
}

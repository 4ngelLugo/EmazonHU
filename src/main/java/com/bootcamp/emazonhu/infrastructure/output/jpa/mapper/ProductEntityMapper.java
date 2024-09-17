package com.bootcamp.emazonhu.infrastructure.output.jpa.mapper;

import com.bootcamp.emazonhu.domain.model.Product;
import com.bootcamp.emazonhu.infrastructure.output.jpa.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductEntityMapper {

    ProductEntity toEntity(Product product);

    Product toProduct(ProductEntity productEntity);

    List<Product> toProductList(List<ProductEntity> productEntityList);
}

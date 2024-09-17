package com.bootcamp.emazonhu.application.mapper.product;

import com.bootcamp.emazonhu.application.dto.product.ProductResponse;
import com.bootcamp.emazonhu.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductResponseMapper {

    private ProductResponse getProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductId(product.getProductId());
        productResponse.setProductName(product.getProductName());
        productResponse.setProductDescription(product.getProductDescription());
        productResponse.setProductQuantity(product.getProductQuantity());
        productResponse.setProductPrice(product.getProductPrice());
        productResponse.setBrandName(product.getProductBrand().getBrandName());
        return productResponse;
    }

    default ProductResponse toResponse(Product product) {
        return getProductResponse(product);
    }

    default List<ProductResponse> toResponseList(List<Product> productList) {
        return productList.stream()
                .map(this::getProductResponse).toList();
    }
}

package com.bootcamp.emazonhu.application.handler.product;

import com.bootcamp.emazonhu.application.dto.product.ProductRequest;
import com.bootcamp.emazonhu.application.dto.product.ProductResponse;

import java.util.List;

public interface IProductHandler {

    void saveProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductId(Long productId);

    ProductResponse getProductName(String productName);

    void updateProduct(ProductRequest productRequest);

    void deleteProduct(Long productId);
}
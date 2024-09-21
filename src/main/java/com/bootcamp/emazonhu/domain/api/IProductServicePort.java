package com.bootcamp.emazonhu.domain.api;

import com.bootcamp.emazonhu.domain.model.Product;

import java.util.List;

public interface IProductServicePort {

    void saveProduct(Product product);

    List<Product> getAllProducts();

    Product getProductId(Long productId);

    Product getProductName(String productName);

    void updateProduct(Product product);

    void deleteProduct(Long productId);
}

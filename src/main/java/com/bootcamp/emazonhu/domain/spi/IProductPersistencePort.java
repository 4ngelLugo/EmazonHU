package com.bootcamp.emazonhu.domain.spi;

import com.bootcamp.emazonhu.domain.model.Product;

import java.util.List;

public interface IProductPersistencePort {

    void saveProduct(Product product);

    List<Product> getAllProducts();

    Product getProductId(Long productId);

    Product getProductName(String productName);

    void updateProduct(Product product);

    void deleteProduct(Long productId);
}

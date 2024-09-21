package com.bootcamp.emazonhu.domain.usecase;

import com.bootcamp.emazonhu.domain.api.IProductServicePort;
import com.bootcamp.emazonhu.domain.model.Product;
import com.bootcamp.emazonhu.domain.spi.IProductPersistencePort;

import java.util.List;

public class ProductUseCase implements IProductServicePort {

    public final IProductPersistencePort productPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public void saveProduct(Product product) {
        if (product.getProductCategories().isEmpty() || product.getProductCategories().size() > 3) {
            return;
        }
        productPersistencePort.saveProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productPersistencePort.getAllProducts();
    }

    @Override
    public Product getProductId(Long productId) {
        return productPersistencePort.getProductId(productId);
    }

    @Override
    public Product getProductName(String productName) {
        return productPersistencePort.getProductName(productName);
    }

    @Override
    public void updateProduct(Product product) {
        productPersistencePort.updateProduct(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productPersistencePort.deleteProduct(productId);
    }
}

package com.bootcamp.emazonhu.infrastructure.output.jpa.adapter;

import com.bootcamp.emazonhu.domain.model.Product;
import com.bootcamp.emazonhu.domain.spi.IProductPersistencePort;
import com.bootcamp.emazonhu.infrastructure.exception.NoDataFoundException;
import com.bootcamp.emazonhu.infrastructure.exception.brand.BrandNotFoundException;
import com.bootcamp.emazonhu.infrastructure.exception.product.ProductAlreadyExistException;
import com.bootcamp.emazonhu.infrastructure.exception.product.ProductNotFoundException;
import com.bootcamp.emazonhu.infrastructure.output.jpa.entity.ProductEntity;
import com.bootcamp.emazonhu.infrastructure.output.jpa.mapper.ProductEntityMapper;
import com.bootcamp.emazonhu.infrastructure.output.jpa.repository.IProductRepository;

import java.util.List;

public class ProductJpaAdapter implements IProductPersistencePort {

    private final IProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;

    public ProductJpaAdapter(IProductRepository productRepository, ProductEntityMapper productEntityMapper) {
        this.productRepository = productRepository;
        this.productEntityMapper = productEntityMapper;
    }


    @Override
    public void saveProduct(Product product) {
        if (product.getProductName() != null && productRepository.findByProductName(product.getProductName()).isPresent()) {
            throw new ProductAlreadyExistException();
        }

        if (product.getProductBrand().getBrandId() == null || product.getProductBrand().getBrandId() == 0) {
            throw new BrandNotFoundException();
        }

        productRepository.save(productEntityMapper.toEntity(product));
    }

    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntityList = productRepository.findAll();

        if (productEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }

        return productEntityMapper.toProductList(productEntityList);
    }

    @Override
    public Product getProductId(Long productId) {
        return productEntityMapper.toProduct(productRepository.findByProductId(productId)
                .orElseThrow(ProductNotFoundException::new));
    }

    @Override
    public Product getProductName(String productName) {
        return productEntityMapper.toProduct(productRepository.findByProductName(productName)
                .orElseThrow(ProductNotFoundException::new));
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(productEntityMapper.toEntity(product));
    }

    @Override
    public void deleteProduct(Long productId) {
        ProductEntity productEntity = productRepository.findByProductId(productId)
                .orElseThrow(ProductNotFoundException::new);
        productRepository.delete(productEntity);
    }
}

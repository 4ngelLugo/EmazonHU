package com.bootcamp.emazonhu.infrastructure.output.jpa.adapter;

import com.bootcamp.emazonhu.domain.model.Category;
import com.bootcamp.emazonhu.domain.model.Product;
import com.bootcamp.emazonhu.domain.spi.IProductPersistencePort;
import com.bootcamp.emazonhu.infrastructure.exception.NoDataFoundException;
import com.bootcamp.emazonhu.infrastructure.exception.brand.BrandNotFoundException;
import com.bootcamp.emazonhu.infrastructure.exception.category.CategoryAlreadyExistException;
import com.bootcamp.emazonhu.infrastructure.exception.category.CategoryNotFoundException;
import com.bootcamp.emazonhu.infrastructure.exception.product.ProductAlreadyExistException;
import com.bootcamp.emazonhu.infrastructure.exception.product.ProductNotFoundException;
import com.bootcamp.emazonhu.infrastructure.exception.product.WrongCategoriesQuantityException;
import com.bootcamp.emazonhu.infrastructure.output.jpa.entity.ProductEntity;
import com.bootcamp.emazonhu.infrastructure.output.jpa.mapper.ProductEntityMapper;
import com.bootcamp.emazonhu.infrastructure.output.jpa.repository.IProductRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        if (product.getProductCategories() == null || product.getProductCategories().isEmpty() || product.getProductCategories().size() > 3) {
            throw new WrongCategoriesQuantityException();
        }

        if (product.getProductCategories().stream().anyMatch(category -> category.getCategoryId() == 0)) {
            throw new CategoryNotFoundException();
        }

        Set<Long> categoryIds = new HashSet<>();
        boolean hasDuplicates = product.getProductCategories().stream()
                .map(Category::getCategoryId)
                .anyMatch(id -> !categoryIds.add(id));

        if (hasDuplicates) {
            throw new CategoryAlreadyExistException();
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

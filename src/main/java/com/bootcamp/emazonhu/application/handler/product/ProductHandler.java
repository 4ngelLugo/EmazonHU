package com.bootcamp.emazonhu.application.handler.product;

import com.bootcamp.emazonhu.application.dto.product.ProductRequest;
import com.bootcamp.emazonhu.application.dto.product.ProductResponse;
import com.bootcamp.emazonhu.application.mapper.product.ProductRequestMapper;
import com.bootcamp.emazonhu.application.mapper.product.ProductResponseMapper;
import com.bootcamp.emazonhu.domain.api.IProductServicePort;
import com.bootcamp.emazonhu.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductHandler implements IProductHandler{

    private final IProductServicePort productServicePort;
    private final ProductRequestMapper productRequestMapper;
    private final ProductResponseMapper productResponseMapper;

    @Override
    public void saveProduct(ProductRequest productRequest) {
        Product product = productRequestMapper.toProduct(productRequest);
        productServicePort.saveProduct(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productResponseMapper.toResponseList(productServicePort.getAllProducts());
    }

    @Override
    public ProductResponse getProductId(Long productId) {
        Product product = productServicePort.getProductId(productId);
        return productResponseMapper.toResponse(product);
    }

    @Override
    public ProductResponse getProductName(String productName) {
        Product product = productServicePort.getProductName(productName);
        return productResponseMapper.toResponse(product);
    }

    @Override
    public void updateProduct(ProductRequest productRequest) {
        Product oldProduct = productServicePort.getProductId(productRequest.getProductId());
        Product newProduct = productRequestMapper.toProduct(productRequest);

        newProduct.setProductId(oldProduct.getProductId());

        productServicePort.updateProduct(newProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productServicePort.deleteProduct(productId);
    }
}

package com.bootcamp.emazonhu.domain.usecase;

import com.bootcamp.emazonhu.domain.model.Brand;
import com.bootcamp.emazonhu.domain.model.Category;
import com.bootcamp.emazonhu.domain.model.Product;
import com.bootcamp.emazonhu.domain.spi.IProductPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductUseCaseTest {

    @Mock
    private IProductPersistencePort productPersistencePort;

    @InjectMocks
    private ProductUseCase productUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveProduct_ValidProduct() {
        // Arrange
        Category category1 = new Category(1L, "Category1", "Description1");
        Category category2 = new Category(2L, "Category2", "Description2");
        Product product = new Product(1L, "Product1", "Description", 10L, 100L, null, List.of(category1, category2));

        doNothing().when(productPersistencePort).saveProduct(product);

        // Act
        productUseCase.saveProduct(product);

        // Assert
        verify(productPersistencePort, times(1)).saveProduct(product);
    }

    @Test
    void saveProduct_InvalidCategoryCount() {
        // Arrange
        Brand brand = new Brand(1L, "Brand", "Description");
        Category category1 = new Category(1L, "Category1", "Description1");
        Category category2 = new Category(2L, "Category2","Description2");
        Category category3 = new Category(3L, "Category3","Description3");
        Category category4 = new Category(4L, "Category4","Description4");

        Product productWithTooManyCategories = new Product(1L, "Product1", "Description", 10L, 100L, brand,List.of(category1, category2, category3, category4));
        Product productWithNoCategories = new Product(2L, "Product2", "Description", 5L, 50L, brand,Collections.emptyList());

        // Act & Assert (no exception should be thrown, just return without saving)
        productUseCase.saveProduct(productWithTooManyCategories);
        productUseCase.saveProduct(productWithNoCategories);

        // Verify that the persistence method is NOT called due to invalid category count
        verify(productPersistencePort, never()).saveProduct(productWithTooManyCategories);
        verify(productPersistencePort, never()).saveProduct(productWithNoCategories);
    }


    @Test
    void getAllProducts() {
        // Arrange
        List<Product> products = List.of(new Product(1L, "Product1", "Description", 10L, 100L, null, Collections.emptyList()));
        when(productPersistencePort.getAllProducts()).thenReturn(products);

        // Act
        List<Product> result = productUseCase.getAllProducts();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Product1", result.get(0).getProductName());
    }

    @Test
    void getProductById_ValidId() {
        // Arrange
        Product product = new Product(1L, "Product1", "Description", 10L, 100L, null, Collections.emptyList());
        when(productPersistencePort.getProductId(1L)).thenReturn(product);

        // Act
        Product result = productUseCase.getProductId(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getProductId());
    }

    @Test
    void getProductById_InvalidId() {
        // Arrange
        when(productPersistencePort.getProductId(999L)).thenReturn(null);

        // Act
        Product result = productUseCase.getProductId(999L);

        // Assert
        assertNull(result); // Handle null gracefully without throwing exceptions
    }

    @Test
    void getProductByName_ValidName() {
        // Arrange
        Product product = new Product(1L, "Product1", "Description", 10L, 100L, null, Collections.emptyList());
        when(productPersistencePort.getProductName("Product1")).thenReturn(product);

        // Act
        Product result = productUseCase.getProductName("Product1");

        // Assert
        assertNotNull(result);
        assertEquals("Product1", result.getProductName());
    }

    @Test
    void updateProduct() {
        // Arrange
        Product product = new Product(1L, "Product1", "Description", 10L, 100L, null, Collections.emptyList());
        doNothing().when(productPersistencePort).updateProduct(product);

        // Act
        productUseCase.updateProduct(product);

        // Assert
        verify(productPersistencePort, times(1)).updateProduct(product);
    }

    @Test
    void deleteProduct() {
        // Arrange
        Long productId = 1L;
        doNothing().when(productPersistencePort).deleteProduct(productId);

        // Act
        productUseCase.deleteProduct(productId);

        // Assert
        verify(productPersistencePort, times(1)).deleteProduct(productId);
    }
}

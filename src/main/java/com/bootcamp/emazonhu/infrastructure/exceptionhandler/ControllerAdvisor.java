package com.bootcamp.emazonhu.infrastructure.exceptionhandler;

import com.bootcamp.emazonhu.infrastructure.exception.brand.BrandAlreadyExistException;
import com.bootcamp.emazonhu.infrastructure.exception.brand.BrandNotFoundException;
import com.bootcamp.emazonhu.infrastructure.exception.category.CategoryAlreadyExistException;
import com.bootcamp.emazonhu.infrastructure.exception.category.CategoryNotFoundException;
import com.bootcamp.emazonhu.infrastructure.exception.NoDataFoundException;
import com.bootcamp.emazonhu.infrastructure.exception.product.ProductAlreadyExistException;
import com.bootcamp.emazonhu.infrastructure.exception.product.ProductNotFoundException;
import com.bootcamp.emazonhu.infrastructure.exception.product.WrongCategoriesQuantityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";

    //Category exceptions
    @ExceptionHandler(CategoryAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExistException(CategoryAlreadyExistException categoryAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap(MESSAGE, ExceptionResponse.CATEGORY_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(CategoryNotFoundException categoryNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(MESSAGE, ExceptionResponse.CATEGORY_NOT_FOUND.getMessage()));
    }

    //Brand Exceptions
    @ExceptionHandler(BrandAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleBrandAlreadyExistException(BrandAlreadyExistException brandAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap(MESSAGE, ExceptionResponse.BRAND_ALREADY_EXIST.getMessage()));
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBrandNotFoundException(BrandNotFoundException brandNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(MESSAGE, ExceptionResponse.BRAND_NOT_FOUND.getMessage()));
    }

    //Product Exceptions
    @ExceptionHandler(ProductAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleProductAlreadyExistException(ProductAlreadyExistException productAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap(MESSAGE, ExceptionResponse.PRODUCT_ALREADY_EXIST.getMessage()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(MESSAGE, ExceptionResponse.PRODUCT_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(WrongCategoriesQuantityException.class)
    public ResponseEntity<Map<String, String>> handleWrongCategoriesQuantityException(WrongCategoriesQuantityException wrongCategoriesQuantityException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap(MESSAGE, ExceptionResponse.WRONG_CATEGORIES_QUANTITY.getMessage()));
    }

    //No data found
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(NoDataFoundException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }
}
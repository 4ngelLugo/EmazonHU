package com.bootcamp.emazonhu.infrastructure.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionResponse {
    CATEGORY_NOT_FOUND("No Category was found"),
    CATEGORY_ALREADY_EXISTS("There is already a category with that name or id"),

    BRAND_NOT_FOUND("No brand was found"),
    BRAND_ALREADY_EXIST("There is already a brand with that name or id"),

    PRODUCT_NOT_FOUND("No product was found"),
    PRODUCT_ALREADY_EXIST("There is already a product with that name or id"),
    WRONG_CATEGORIES_QUANTITY("The number of categories for a product must be between 1 and 3"),

    NO_DATA_FOUND("No data found for the requested petition");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

}
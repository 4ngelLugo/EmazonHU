package com.bootcamp.emazonhu.infrastructure.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionResponse {
    CATEGORY_NOT_FOUND("No Category was found"),
    BRAND_NOT_FOUND("No brand was found"),
    PRODUCT_NOT_FOUND("No product was found"),
    CATEGORY_ALREADY_EXISTS("There is already a category with that name"),
    BRAND_ALREADY_EXIST("There is already a brand with that name"),
    PRODUCT_ALREADY_EXIST("There is already a product with that name"),
    NO_DATA_FOUND("No data found for the requested petition");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

}
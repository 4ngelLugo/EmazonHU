package com.bootcamp.emazonhu.application.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Long productId;
    private String productName;
    private String productDescription;
    private Long productQuantity;
    private Long productPrice;

    private String brandName;
}

package com.bootcamp.emazonhu.application.dto.product;

import com.bootcamp.emazonhu.domain.model.Brand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    private Long productId;
    private String productName;
    private String productDescription;
    private Long productQuantity;
    private Long productPrice;

    private Brand productBrand;
}

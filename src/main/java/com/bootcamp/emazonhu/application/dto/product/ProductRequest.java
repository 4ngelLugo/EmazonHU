package com.bootcamp.emazonhu.application.dto.product;

import com.bootcamp.emazonhu.domain.model.Brand;
import com.bootcamp.emazonhu.domain.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductRequest {

    private Long productId;
    private String productName;
    private String productDescription;
    private Long productQuantity;
    private Long productPrice;

    private Brand productBrand;
    private List<Category> productCategories;
}

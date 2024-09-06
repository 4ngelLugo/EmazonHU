package com.bootcamp.emazonhu.application.dto.brand;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandRequest {

    private Long brandId;
    private String brandName;
    private String brandDescription;
}

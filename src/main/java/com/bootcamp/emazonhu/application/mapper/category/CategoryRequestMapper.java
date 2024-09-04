package com.bootcamp.emazonhu.application.mapper.category;

import com.bootcamp.emazonhu.application.dto.category.CategoryRequest;
import com.bootcamp.emazonhu.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryRequestMapper {

    Category toCategory(CategoryRequest categoryRequest);
}

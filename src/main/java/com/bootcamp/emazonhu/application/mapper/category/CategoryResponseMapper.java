package com.bootcamp.emazonhu.application.mapper.category;

import com.bootcamp.emazonhu.application.dto.category.CategoryResponse;
import com.bootcamp.emazonhu.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryResponseMapper {

    CategoryResponse toResponse(Category category);

    default List<CategoryResponse> toResonseList(List<Category> categoryList) {
        return categoryList.stream()
                .map(category -> {
                    CategoryResponse categoryResponse = new CategoryResponse();
                    categoryResponse.setCategoryId(category.getCategoryId());
                    categoryResponse.setCategoryName(category.getCategoryName());
                    categoryResponse.setCategoryDescription(category.getCategoryDescription());
                    return categoryResponse;
                }).toList();
    }
}

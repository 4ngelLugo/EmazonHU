package com.bootcamp.emazonhu.infrastructure.input.rest;

import com.bootcamp.emazonhu.application.dto.category.CategoryRequest;
import com.bootcamp.emazonhu.application.dto.category.CategoryResponse;
import com.bootcamp.emazonhu.application.handler.category.ICategoryHandler;
import com.bootcamp.emazonhu.domain.model.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @Operation(summary = "Add new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Category already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryHandler.saveCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all the categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All categories returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "categoryName") String sortBy,
            @RequestParam(defaultValue = "true") Boolean asc
    ) {
        return ResponseEntity.ok(categoryHandler.getAllCategories(page, size, sortBy, asc));
    }

    @Operation(summary = "Get a category by their Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @GetMapping("/id/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategoryId(
            @Parameter(description = "Id of the category to be returned")
            @PathVariable(name = "categoryId") Long categoryId) {
        return ResponseEntity.ok(categoryHandler.getCategoryId(categoryId));
    }

    @Operation(summary = "Get a category by their Name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @GetMapping("/name/{categoryName}")
    public ResponseEntity<CategoryResponse> getCategoryName(
            @Parameter(description = "Name of the category to be returned")
            @PathVariable(name = "categoryName") String categoryName) {
        return ResponseEntity.ok(categoryHandler.getCategoryName(categoryName));
    }

    @Operation(summary = "Update an existing category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @PutMapping("/")
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryHandler.updateCategory(categoryRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete an existing category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryHandler.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}

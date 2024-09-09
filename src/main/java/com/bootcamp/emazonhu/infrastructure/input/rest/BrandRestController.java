package com.bootcamp.emazonhu.infrastructure.input.rest;

import com.bootcamp.emazonhu.application.dto.brand.BrandRequest;
import com.bootcamp.emazonhu.application.dto.brand.BrandResponse;
import com.bootcamp.emazonhu.application.handler.brand.IBrandHandler;
import com.bootcamp.emazonhu.domain.model.Brand;
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
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandRestController {

    private final IBrandHandler brandHandler;

    @Operation(summary = "Create new brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand Created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Brand already exist", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveBrand(@RequestBody BrandRequest brandRequest) {
        brandHandler.saveBrand(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all the brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All brands returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BrandResponse.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<Brand>> getAllCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "brandName") String sortBy,
            @RequestParam(defaultValue = "true") Boolean asc
    ) {
        return ResponseEntity.ok(brandHandler.getAllBrands(page, size, sortBy, asc));
    }


    @Operation(summary = "Get a brand by their Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrandResponse.class))),
            @ApiResponse(responseCode = "404", description = "Brand not found", content = @Content)
    })
    @GetMapping("/id/{brandId}")
    public ResponseEntity<BrandResponse> getBrandId(
            @Parameter(description = "Id of the brand to be returned")
            @PathVariable(name = "brandId") Long brandId) {
        return ResponseEntity.ok(brandHandler.getBrandId(brandId));
    }

    @Operation(summary = "Get a brand by their Name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrandResponse.class))),
            @ApiResponse(responseCode = "404", description = "Brand not found", content = @Content)
    })
    @GetMapping("/name/{brandName}")
    public ResponseEntity<BrandResponse> getBrandName(
            @Parameter(description = "Name of the brand to be returned")
            @PathVariable(name = "brandName") String brandName) {
        return ResponseEntity.ok(brandHandler.getBrandName(brandName));
    }

    @Operation(summary = "Update an existing brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Brand updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Brand not found", content = @Content)
    })
    @PutMapping("/")
    public ResponseEntity<Void> updateBrand(@RequestBody BrandRequest brandRequest) {
        brandHandler.updateBrand(brandRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete an existing brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Brand deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Brand not found", content = @Content)
    })
    @DeleteMapping("/{brandId}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long brandId) {
        brandHandler.deleteBrand(brandId);
        return ResponseEntity.noContent().build();
    }
}

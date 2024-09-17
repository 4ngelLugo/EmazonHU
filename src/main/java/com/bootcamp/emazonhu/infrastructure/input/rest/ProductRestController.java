package com.bootcamp.emazonhu.infrastructure.input.rest;

import com.bootcamp.emazonhu.application.dto.product.ProductRequest;
import com.bootcamp.emazonhu.application.dto.product.ProductResponse;
import com.bootcamp.emazonhu.application.handler.product.IProductHandler;
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
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestController {

    private final IProductHandler productHandler;

    @Operation(summary = "Create new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product Created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Product already exist", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveProduct(@RequestBody ProductRequest productRequest) {
        productHandler.saveProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all the product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All product returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductResponse.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productHandler.getAllProducts());
    }

    @Operation(summary = "Get a product by their Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    @GetMapping("/id/{productId}")
    public ResponseEntity<ProductResponse> getProductId(
            @Parameter(description = "Id of the product to be returned")
            @PathVariable(name = "productId") Long productId) {
        return ResponseEntity.ok(productHandler.getProductId(productId));
    }

    @Operation(summary = "Get a product by their Name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    @GetMapping("/name/{productName}")
    public ResponseEntity<ProductResponse> getProductName(
            @Parameter(description = "Name of the product to be returned")
            @PathVariable(name = "productName") String productName) {
        return ResponseEntity.ok(productHandler.getProductName(productName));
    }

    @Operation(summary = "Update an existing product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    @PutMapping("/")
    public ResponseEntity<Void> updateProduct(@RequestBody ProductRequest productRequest) {
        productHandler.updateProduct(productRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete an existing product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productHandler.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}

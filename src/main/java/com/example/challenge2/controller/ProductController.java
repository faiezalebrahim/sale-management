package com.example.challenge2.controller;

import com.example.challenge2.dto.request.CreateProductRequest;
import com.example.challenge2.dto.request.UpdateProductRequest;
import com.example.challenge2.dto.response.ProductResponse;
import com.example.challenge2.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Products")
@RestController
@RequestMapping("products")
@Validated
public class ProductController {
    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    @Operation(
            summary = "Get All Products",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/")
    public List<ProductResponse> getAllProducts(
    ) {
        var products = productService.getAll();

        return modelMapper.map(products, new TypeToken<List<ProductResponse>>() {
        }.getType());
    }

    @Operation(
            summary = "Get Product by ID",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        var product = productService.get(id);
        return modelMapper.map(product, ProductResponse.class);
    }

    @Operation(
            summary = "Create New Product",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping("/")
    public ProductResponse createProduct(@Valid @RequestBody CreateProductRequest request) {
        var product = productService.create(request);
        return modelMapper.map(product, ProductResponse.class);
    }

    @Operation(
            summary = "Update Existing Product",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateProductRequest request
    ) {
        var product = productService.update(id, request);
        return modelMapper.map(product, ProductResponse.class);
    }

    @Operation(
            summary = "Delete Existing Product",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.delete(id);
    }
}

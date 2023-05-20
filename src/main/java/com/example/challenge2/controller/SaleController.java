package com.example.challenge2.controller;

import com.example.challenge2.dto.request.CreateSaleRequest;
import com.example.challenge2.dto.request.UpdateSaleRequest;
import com.example.challenge2.dto.response.SaleResponse;
import com.example.challenge2.service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Sales")
@RestController
@RequestMapping("sales")
@Validated
@Log4j2
public class SaleController {
    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    private SaleService saleService;

    @Operation(
            summary = "Get All Sales",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/")
    public List<SaleResponse> getAllSales(
    ) {
        log.info("Get All Sales");
        var sales = saleService.getAll();

        return modelMapper.map(sales, new TypeToken<List<SaleResponse>>() {
        }.getType());
    }

    @Operation(
            summary = "Get Sale by ID",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/{id}")
    public SaleResponse getSale(@PathVariable Long id) {
        log.info("Get Sale By Id");
        var sale = saleService.get(id);
        return modelMapper.map(sale, SaleResponse.class);
    }

    @Operation(
            summary = "Create New Sale",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping("/")
    public SaleResponse createSale(@Valid @RequestBody CreateSaleRequest request) {
        log.info("Create Sale");
        var sale = saleService.create(request);
        return modelMapper.map(sale, SaleResponse.class);
    }

    @Operation(
            summary = "Update Existing Sale",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PutMapping("/{id}")
    public SaleResponse updateSale(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateSaleRequest request
    ) {
        log.info("Update Sale");
        var sale = saleService.update(id, request);
        return modelMapper.map(sale, SaleResponse.class);
    }

    @Operation(
            summary = "Delete Existing Sale",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable("id") Long id) {
        saleService.delete(id);
    }
}

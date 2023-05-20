package com.example.challenge2.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaleResponse extends DetailsResponse{
    @NotNull
    private Long id;

    @NotNull
    private ClientResponse client;

    private String seller;

    private Integer quantity;

    private Double price;

    private Double totalPrice;
}

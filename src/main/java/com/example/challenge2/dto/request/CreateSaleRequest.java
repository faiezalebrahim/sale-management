package com.example.challenge2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateSaleRequest {
    @NotNull(message = "ClientId not null")
    private Long clientId;

    private String seller;

    private Integer quantity;

    private Double price;
}

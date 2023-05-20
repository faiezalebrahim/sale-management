package com.example.challenge2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateProductRequest {
    @NotNull(message = "Name can't be null")
    private String name;

    private String description;

    private String category;
}

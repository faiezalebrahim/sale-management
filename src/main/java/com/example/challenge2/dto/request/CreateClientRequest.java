package com.example.challenge2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateClientRequest {
    @NotNull(message = "Name can't be null")
    private String name;

    private String lastName;

    @Pattern(regexp = "^(?:\\+?\\d{1,3})?[-.\\s]?\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$"
            , message = "Must be a valid number")
    private Integer mobile;
}

package com.example.challenge2.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientResponse extends DetailsResponse{
    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String lastName;

    private Integer mobile;
}

package com.example.challenge2.controller;

import com.example.challenge2.dto.request.CreateClientRequest;
import com.example.challenge2.dto.request.UpdateClientRequest;
import com.example.challenge2.dto.response.ClientResponse;
import com.example.challenge2.service.ClientService;
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

@Tag(name = "Clients")
@RestController
@RequestMapping("clients")
@Validated
public class ClientController {
    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    private ClientService clientService;

    @Operation(
            summary = "Get All Clients",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/")
    public List<ClientResponse> getAllClients(
    ) {
        var clientsManagements = clientService.getAll();

        return modelMapper.map(clientsManagements, new TypeToken<List<ClientResponse>>() {
        }.getType());
    }

    @Operation(
            summary = "Get Client by ID",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/{id}")
    public ClientResponse getClient(@PathVariable Long id) {
        var client = clientService.get(id);
        return modelMapper.map(client, ClientResponse.class);
    }

    @Operation(
            summary = "Create New Client",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping("/")
    public ClientResponse createClient(@Valid @RequestBody CreateClientRequest request) {
        var client = clientService.create(request);
        return modelMapper.map(client, ClientResponse.class);
    }

    @Operation(
            summary = "Update Existing Client",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PutMapping("/{id}")
    public ClientResponse updateClient(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateClientRequest request
    ) {
        var client = clientService.update(id, request);
        return modelMapper.map(client, ClientResponse.class);
    }

    @Operation(
            summary = "Delete Existing Client",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable("id") Long id) {
        clientService.delete(id);
    }
}

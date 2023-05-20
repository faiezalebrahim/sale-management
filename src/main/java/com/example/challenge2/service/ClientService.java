package com.example.challenge2.service;

import com.example.challenge2.dto.request.CreateClientRequest;
import com.example.challenge2.dto.request.UpdateClientRequest;
import com.example.challenge2.entity.Client;
import com.example.challenge2.repository.ClientRepository;
import com.google.common.collect.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        var clients = clientRepository.findAll();

        return Arrays
                .stream(Iterators.toArray(clients.iterator(), Client.class))
                .collect(Collectors.toList());
    }

    public Client get(Long id) {
        var client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new EntityNotFoundException("Client not found");
        }

        return client.get();
    }

    @Transactional
    public Client create(CreateClientRequest request) {
        var client = new Client();

        client.setCreatedAt(LocalDateTime.now());

        client.setName(request.getName());

        if (request.getLastName() != null) {
            client.setLastName(request.getLastName());
        } else {
            client.setLastName(null);
        }

        if (request.getMobile() != null) {
            client.setMobile(request.getMobile());
        } else {
            client.setMobile(null);
        }

        return clientRepository.save(client);
    }

    @Transactional
    public Client update(Long id, UpdateClientRequest request) {
        var client = get(id);
        client.refresh();

        client.setName(request.getName());

        if (request.getLastName() != null) {
            client.setLastName(request.getLastName());
        } else {
            client.setLastName(null);
        }

        if (request.getMobile() != null) {
            client.setMobile(request.getMobile());
        } else {
            client.setMobile(null);
        }

        return clientRepository.save(client);
    }

    @Transactional
    public void delete(Long id) {
        var client = get(id);

        if (client.getDeletedAt() != null) {
            throw new RuntimeException("Already deleted");
        }
        client.delete();

        clientRepository.save(client);
    }
}

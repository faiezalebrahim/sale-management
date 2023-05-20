package com.example.challenge2.service;

import com.example.challenge2.dto.request.CreateSaleRequest;
import com.example.challenge2.dto.request.UpdateSaleRequest;
import com.example.challenge2.entity.Sale;
import com.example.challenge2.repository.SaleRepository;
import com.google.common.collect.Iterators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {
    private static final Logger logger = LoggerFactory.getLogger(SaleService.class);

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientService clientService;

    public List<Sale> getAll() {
        var sales = saleRepository.findAll();

        return Arrays
                .stream(Iterators.toArray(sales.iterator(), Sale.class))
                .collect(Collectors.toList());
    }

    public Sale get(Long id) {
        var sale = saleRepository.findById(id);

        if (sale.isEmpty()) {
            throw new EntityNotFoundException("Sale not found");
        }

        return sale.get();
    }

    @Transactional
    public Sale create(CreateSaleRequest request) {
        var sale = new Sale();

        sale.setCreatedAt(LocalDateTime.now());

        var client = clientService.get(request.getClientId());
        sale.setClient(client);

        sale.setPrice(request.getPrice());
        sale.setQuantity(request.getQuantity());

        if (request.getSeller() != null) {
            sale.setSeller(request.getSeller());
        } else {
            sale.setSeller(null);
        }

        return saleRepository.save(sale);
    }

    @Transactional
    public Sale update(Long id, UpdateSaleRequest request) {
        var sale = get(id);
        sale.refresh();

        var client = clientService.get(request.getClientId());
        sale.setClient(client);

        sale.setPrice(request.getPrice());
        sale.setQuantity(request.getQuantity());

        if (request.getSeller() != null) {
            sale.setSeller(request.getSeller());
        } else {
            sale.setSeller(null);
        }

        logger.info("Sale transaction updated by: "+ sale.getClient().getName());

        return saleRepository.save(sale);
    }

    @Transactional
    public void delete(Long id) {
        var sale = get(id);

        if (sale.getDeletedAt() != null) {
            throw new RuntimeException("Already deleted");
        }
        sale.delete();

        saleRepository.save(sale);
    }
}

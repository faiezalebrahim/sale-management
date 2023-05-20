package com.example.challenge2.service;

import com.example.challenge2.dto.request.CreateProductRequest;
import com.example.challenge2.dto.request.UpdateProductRequest;
import com.example.challenge2.entity.Product;
import com.example.challenge2.repository.ProductRepository;
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
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        var products = productRepository.findAll();

        return Arrays
                .stream(Iterators.toArray(products.iterator(), Product.class))
                .collect(Collectors.toList());
    }

    public Product get(Long id) {
        var product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }

        return product.get();
    }

    @Transactional
    public Product create(CreateProductRequest request) {
        var product = new Product();

        product.setCreatedAt(LocalDateTime.now());

        product.setName(request.getName());

        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        } else {
            product.setDescription(null);
        }

        if (request.getCategory() != null) {
            product.setCategory(request.getCategory());
        } else {
            product.setCategory(null);
        }

        return productRepository.save(product);
    }

    @Transactional
    public Product update(Long id, UpdateProductRequest request) {
        var product = get(id);
        product.refresh();

        product.setName(request.getName());

        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        } else {
            product.setDescription(null);
        }

        if (request.getCategory() != null) {
            product.setCategory(request.getCategory());
        } else {
            product.setCategory(null);
        }

        return productRepository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        var product = get(id);

        if (product.getDeletedAt() != null) {
            throw new RuntimeException("Already deleted");
        }
        product.delete();

        productRepository.save(product);
    }
}

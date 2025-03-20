package com.mlastovsky.service;

import com.mlastovsky.exception.ProductNotFoundException;
import com.mlastovsky.exception.ProductPurchaseException;
import com.mlastovsky.mapper.ProductMapper;
import com.mlastovsky.model.*;
import com.mlastovsky.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Product createProduct(@Valid ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product);
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        var productIds = extractProductIds(requests);

        var storedProducts = getStoredProducts(productIds);
        var storedRequests = sortRequestsById(requests);

        return processProductPurchases(storedProducts, storedRequests);
    }

    private List<Long> extractProductIds(List<ProductPurchaseRequest> requests) {
        return requests.stream()
                .map(ProductPurchaseRequest::id)
                .toList();
    }

    private List<Product> getStoredProducts(List<Long> productIds) {
        var storedProducts = repository.findAllByIdIn(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more product does not exist");
        }
        return storedProducts;
    }

    private List<ProductPurchaseRequest> sortRequestsById(List<ProductPurchaseRequest> requests) {
        return requests.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::id))
                .toList();
    }

    private List<ProductPurchaseResponse> processProductPurchases(List<Product> storedProducts, List<ProductPurchaseRequest> storedRequests) {
        return IntStream.range(0, storedProducts.size())
                .mapToObj(i -> processSingleProduct(storedProducts.get(i), storedRequests.get(i)))
                .toList();
    }

    private ProductPurchaseResponse processSingleProduct(Product product, ProductPurchaseRequest request) {
        if (product.getAvailableQuantity() < request.quantity()) {
            throw new ProductPurchaseException(String.format("Insufficient stock quantity for product with ID: %d", request.id()));
        }
        product.setAvailableQuantity(product.getAvailableQuantity() - request.quantity());
        repository.save(product);
        return mapper.toProductPurchaseResponse(product, request.quantity());
    }

    public ProductResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException(
                                format("Product not found with ID:: %s", id)
                        )
                );
    }

    public List<ProductResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toProductResponse)
                .toList();
    }

}

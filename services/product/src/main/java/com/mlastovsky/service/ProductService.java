package com.mlastovsky.service;

import com.mlastovsky.exception.ProductNotFoundException;
import com.mlastovsky.exception.ProductPurchaseException;
import com.mlastovsky.mapper.ProductMapper;
import com.mlastovsky.model.*;
import com.mlastovsky.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.*;
import static java.util.List.*;

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
        var aggregatedQuantities = aggregateProductQuantities(requests);
        var productIds = copyOf(aggregatedQuantities.keySet());
        var storedProducts = fetchProductsByIds(productIds);

        return storedProducts.stream()
                .map(product -> processSingleProduct(product, aggregatedQuantities.get(product.getId())))
                .collect(Collectors.toList());
    }

    private Map<Long, Double> aggregateProductQuantities(List<ProductPurchaseRequest> requests) {
        return requests.stream()
                .collect(Collectors.groupingBy(
                        ProductPurchaseRequest::id,
                        Collectors.summingDouble(ProductPurchaseRequest::quantity)
                ));
    }

    private List<Product> fetchProductsByIds(List<Long> productIds) {
        var storedProducts = repository.findAllByIdIn(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more product does not exist");
        }
        return storedProducts;
    }

    private ProductPurchaseResponse processSingleProduct(Product product, Double totalQuantity) {
        if (product.getAvailableQuantity() < totalQuantity) {
            throw new ProductPurchaseException(
                    format("Insufficient stock for product with ID: %d", product.getId())
            );
        }
        product.setAvailableQuantity(product.getAvailableQuantity() - totalQuantity);
        repository.save(product);
        return mapper.toProductPurchaseResponse(product, totalQuantity);
    }

    public ProductResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException(
                                format("Product not found with ID:: %d", id)
                        )
                );
    }

    public List<ProductResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toProductResponse)
                .toList();
    }

}

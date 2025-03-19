package com.mlastovsky.mapper;

import com.mlastovsky.model.Category;
import com.mlastovsky.model.Product;
import com.mlastovsky.model.ProductRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(@Valid ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .availableQuantity(request.availableQuantity())
                .category(Category.builder()
                        .id(request.categoryId())
                        .build())
                .build();
    }

}

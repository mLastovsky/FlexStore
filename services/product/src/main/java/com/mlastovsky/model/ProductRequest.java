package com.mlastovsky.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(

        Long id,

        @NotNull(message = "Product name is required")
        String name,

        @NotNull(message = "Product description is required")
        String description,

        @Positive(message = "Available quantity should be positive")
        @NotNull(message = "Product available quantity is required")
        Double availableQuantity,

        @Positive(message = "Price should be positive")
        @NotNull(message = "Product price is required")
        BigDecimal price,

        @NotNull(message = "Product category is required")
        Long categoryId
) {
}

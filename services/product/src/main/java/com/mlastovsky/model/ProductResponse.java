package com.mlastovsky.model;

import java.math.BigDecimal;

public record ProductResponse(

        Long id,

        String name,

        String description,

        Double availableQuantity,

        BigDecimal price,

        Long categoryId,

        String categoryName,

        String categoryDescription
) {
}

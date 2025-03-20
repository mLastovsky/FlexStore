package com.mlastovsky.model;

import java.math.BigDecimal;

public record ProductPurchaseResponse(

        Long id,

        String name,

        String description,

        BigDecimal price,

        Double quantity
) {
}

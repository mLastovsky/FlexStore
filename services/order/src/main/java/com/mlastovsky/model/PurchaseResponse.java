package com.mlastovsky.model;

import java.math.BigDecimal;

public record PurchaseResponse(

        Long id,

        String name,

        String description,

        BigDecimal price,

        Double quantity
) {
}

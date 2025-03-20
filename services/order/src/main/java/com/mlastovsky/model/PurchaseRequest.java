package com.mlastovsky.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(

        @NotNull(message = "Product is mandatory")
        Long id,

        @Positive(message = "Quantity is mandatory")
        Double quantity
) {
}

package com.mlastovsky.model;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest (

        @NotNull(message = "Product id is mandatory")
        Long id,

        @NotNull(message = "Product quantity is mandatory")
        Double quantity
){
}

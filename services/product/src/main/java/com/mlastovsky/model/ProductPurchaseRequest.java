package com.mlastovsky.model;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest (

        @NotNull(message = "Product is mandatory")
        Long id,

        @NotNull(message = "Product is mandatory")
        Double quantity
){
        
}

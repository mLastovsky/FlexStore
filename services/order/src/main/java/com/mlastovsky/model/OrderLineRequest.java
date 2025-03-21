package com.mlastovsky.model;

public record OrderLineRequest (

        Long id,

        Long orderId,

        Long productId,

        Double quantity
) {
}

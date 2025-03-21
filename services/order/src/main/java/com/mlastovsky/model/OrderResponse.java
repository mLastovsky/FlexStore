package com.mlastovsky.model;

import java.math.BigDecimal;

public record OrderResponse(

        Long id,

        String reference,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        String customerId
) {
}

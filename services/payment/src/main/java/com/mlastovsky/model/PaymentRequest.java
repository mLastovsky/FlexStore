package com.mlastovsky.model;

import java.math.BigDecimal;

public record PaymentRequest(

        Long id,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        Long orderId,

        String orderReference,

        Customer customer
) {
}

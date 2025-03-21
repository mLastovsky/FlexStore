package com.mlastovsky.model;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,

        PaymentMethod paymentMethod,

        Long orderId,

        String orderReference,

        CustomerResponse customer
) {
}

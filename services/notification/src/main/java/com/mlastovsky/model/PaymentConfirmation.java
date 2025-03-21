package com.mlastovsky.model;

import java.math.BigDecimal;

public record PaymentConfirmation(

        String orderReference,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        String customerFirstname,

        String customerLastname,

        String customerEmail
) {
}

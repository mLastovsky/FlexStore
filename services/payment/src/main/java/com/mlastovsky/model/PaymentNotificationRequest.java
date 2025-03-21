package com.mlastovsky.model;

import java.math.BigDecimal;

public record PaymentNotificationRequest (

        String orderReference,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        String customerFirstname,

        String customerLastname,

        String customerEmail
) {
}

package com.mlastovsky.model;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String orderReference,

        BigDecimal totalAmount,

        PaymentMethod paymentMethod,

        CustomerResponse customerResponse,

        List<PurchaseResponse> products
) {
}

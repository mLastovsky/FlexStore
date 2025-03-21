package com.mlastovsky.mapper;

import com.mlastovsky.model.Payment;
import com.mlastovsky.model.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();
    }

}

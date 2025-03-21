package com.mlastovsky.service;

import com.mlastovsky.kafka.NotificationProducer;
import com.mlastovsky.mapper.PaymentMapper;
import com.mlastovsky.model.PaymentNotificationRequest;
import com.mlastovsky.model.PaymentRequest;
import com.mlastovsky.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Long createPayment(PaymentRequest request) {
        var payment = paymentRepository.save(mapper.toPayment(request));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );

        return payment.getId();
    }

}

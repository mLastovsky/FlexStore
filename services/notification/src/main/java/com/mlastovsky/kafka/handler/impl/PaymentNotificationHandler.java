package com.mlastovsky.kafka.handler.impl;

import com.mlastovsky.kafka.handler.NotificationHandler;
import com.mlastovsky.model.EmailTemplate;
import com.mlastovsky.model.Notification;
import com.mlastovsky.model.PaymentConfirmation;
import com.mlastovsky.repository.NotificationRepository;
import com.mlastovsky.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

import static com.mlastovsky.model.NotificationType.PAYMENT_CONFIRMATION;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentNotificationHandler implements NotificationHandler<PaymentConfirmation> {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @Override
    public void handle(PaymentConfirmation paymentConfirmation) {
        log.info("Processing payment notification: {}", paymentConfirmation);

        notificationRepository.save(
                Notification.builder()
                        .notificationType(PAYMENT_CONFIRMATION)
                        .notificationDateTime(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        var customerName =
                paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();

        emailService.sendEmail(
                paymentConfirmation.customerEmail(),
                EmailTemplate.PAYMENT_CONFIRMATION.getSubject(),
                EmailTemplate.PAYMENT_CONFIRMATION.getTemplate(),
                Map.of(
                        "customerName", customerName,
                        "amount", paymentConfirmation.amount(),
                        "orderReference", paymentConfirmation.orderReference()
                )
        );
    }

}

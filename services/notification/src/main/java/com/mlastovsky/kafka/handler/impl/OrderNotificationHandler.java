package com.mlastovsky.kafka.handler.impl;

import com.mlastovsky.kafka.handler.NotificationHandler;
import com.mlastovsky.model.EmailTemplate;
import com.mlastovsky.model.Notification;
import com.mlastovsky.model.OrderConfirmation;
import com.mlastovsky.repository.NotificationRepository;
import com.mlastovsky.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

import static com.mlastovsky.model.NotificationType.ORDER_CONFIRMATION;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderNotificationHandler implements NotificationHandler<OrderConfirmation> {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @Override
    public void handle(OrderConfirmation orderConfirmation) {
        log.info("Processing order confirmation: {}", orderConfirmation);

        notificationRepository.save(
                Notification.builder()
                        .notificationType(ORDER_CONFIRMATION)
                        .notificationDateTime(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        var customerName =
                orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();

        emailService.sendEmail(
                orderConfirmation.customer().email(),
                EmailTemplate.ORDER_CONFIRMATION.getSubject(),
                EmailTemplate.ORDER_CONFIRMATION.getTemplate(),
                Map.of(
                        "customerName", customerName,
                        "totalAmount", orderConfirmation.totalAmount(),
                        "orderReference", orderConfirmation.orderReference(),
                        "products", orderConfirmation.products()
                )
        );
    }

}

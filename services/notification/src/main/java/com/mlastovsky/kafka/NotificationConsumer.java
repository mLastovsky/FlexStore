package com.mlastovsky.kafka;

import com.mlastovsky.kafka.handler.impl.OrderNotificationHandler;
import com.mlastovsky.kafka.handler.impl.PaymentNotificationHandler;
import com.mlastovsky.model.OrderConfirmation;
import com.mlastovsky.model.PaymentConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final PaymentNotificationHandler paymentNotificationHandler;
    private final OrderNotificationHandler orderNotificationHandler;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentNotification(PaymentConfirmation paymentConfirmation) {
        log.info("Received payment notification: {}", paymentConfirmation);
        paymentNotificationHandler.handle(paymentConfirmation);
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderNotification(OrderConfirmation orderConfirmation) {
        log.info("Received order notification: {}", orderConfirmation);
        orderNotificationHandler.handle(orderConfirmation);
    }

}

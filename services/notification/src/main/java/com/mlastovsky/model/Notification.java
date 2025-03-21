package com.mlastovsky.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;

    private NotificationType notificationType;

    private LocalDateTime notificationDateTime;

    private OrderConfirmation orderConfirmation;

    private PaymentConfirmation paymentConfirmation;

}

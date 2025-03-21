package com.mlastovsky.mapper;

import com.mlastovsky.model.Order;
import com.mlastovsky.model.OrderRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

}

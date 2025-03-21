package com.mlastovsky.mapper;

import com.mlastovsky.model.Order;
import com.mlastovsky.model.OrderLine;
import com.mlastovsky.model.OrderLineRequest;
import com.mlastovsky.model.OrderLineResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(Order.builder()
                        .id(request.productId())
                        .build())
                .productId(request.productId())
                .build();
    }

    public OrderLineResponse fromOrderLine(OrderLine orderLine) {
        return new OrderLineResponse(

        );
    }

}

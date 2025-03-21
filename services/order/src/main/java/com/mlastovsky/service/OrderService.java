package com.mlastovsky.service;

import com.mlastovsky.exception.BusinessException;
import com.mlastovsky.exception.OrderNotFoundException;
import com.mlastovsky.kafka.OrderProducer;
import com.mlastovsky.mapper.OrderMapper;
import com.mlastovsky.model.OrderConfirmation;
import com.mlastovsky.model.OrderLineRequest;
import com.mlastovsky.model.OrderRequest;
import com.mlastovsky.model.OrderResponse;
import com.mlastovsky.proxy.CustomerProxy;
import com.mlastovsky.proxy.ProductProxy;
import com.mlastovsky.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerProxy customerProxy;
    private final ProductProxy productProxy;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Long createOrder(OrderRequest request) {
        var customer = customerProxy.findCustomerById(request.id())
                .orElseThrow(() -> new BusinessException(
                        format("Cannot create order:: No customer exists with provided id:: %d", request.id())
                ));

        var purchasedProducts = productProxy.purchaseProducts(request.products());

        var order = orderRepository.save(mapper.toOrder(request));

        for (var purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.id(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //todo start payment process

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(mapper::fromOrder)
                .toList();
    }

    public OrderResponse findById(Long id) {
        return orderRepository.findById(id)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new OrderNotFoundException(
                        format("No order found with the provided id:: %d", id)
                ));
    }

}

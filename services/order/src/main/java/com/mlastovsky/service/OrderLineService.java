package com.mlastovsky.service;

import com.mlastovsky.mapper.OrderLineMapper;
import com.mlastovsky.model.OrderLineRequest;
import com.mlastovsky.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Long saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return orderLineRepository.save(order).getId();
    }

}

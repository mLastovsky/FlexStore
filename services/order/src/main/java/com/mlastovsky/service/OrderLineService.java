package com.mlastovsky.service;

import com.mlastovsky.mapper.OrderLineMapper;
import com.mlastovsky.model.OrderLineRequest;
import com.mlastovsky.model.OrderLineResponse;
import com.mlastovsky.repository.OrderLineRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public void saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        orderLineRepository.save(order);
    }

    public List<OrderLineResponse> findAllByOrderId(Long id) {
        return orderLineRepository.findAllByOrderId(id).stream()
                .map(mapper::fromOrderLine)
                .toList();
    }

}

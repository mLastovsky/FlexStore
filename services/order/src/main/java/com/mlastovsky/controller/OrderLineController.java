package com.mlastovsky.controller;

import com.mlastovsky.model.OrderLineResponse;
import com.mlastovsky.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService orderLineService;

    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(
            @RequestParam("id") Long id
    ) {
        return ResponseEntity.ok(orderLineService.findAllByOrderId(id));
    }

}

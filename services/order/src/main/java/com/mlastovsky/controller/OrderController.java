package com.mlastovsky.controller;

import com.mlastovsky.model.OrderRequest;
import com.mlastovsky.model.OrderResponse;
import com.mlastovsky.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> createOrder(
            @RequestBody @Valid OrderRequest request
    ) {
        return ResponseEntity.ok().body(orderService.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(orderService.findById(id));
    }

}

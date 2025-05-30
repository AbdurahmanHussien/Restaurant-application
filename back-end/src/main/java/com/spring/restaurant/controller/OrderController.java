package com.spring.restaurant.controller;


import com.spring.restaurant.dto.OrderItemDto;
import com.spring.restaurant.request.OrderRequest;
import com.spring.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderItemDto> createOrder(@RequestBody OrderRequest request,
                                                    @RequestParam Long userId) {
        return ResponseEntity.ok(orderService.createOrder(request, userId));

    }
}

package com.spring.restaurant.controller;


import com.spring.restaurant.dto.OrderItemDto;
import com.spring.restaurant.request.OrderRequest;
import com.spring.restaurant.service.auth.CustomUserDetails;
import com.spring.restaurant.service.impl.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;



    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAllOrders( @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(orderService.getAllOrders(page, size));
    }

    @PostMapping
    public ResponseEntity<OrderItemDto> createOrder(@Valid @RequestBody OrderRequest request , @AuthenticationPrincipal UserDetails user) {
        Long userId = ((CustomUserDetails) user).getId();
        request.setUserId(userId);
        return ResponseEntity.ok(orderService.createOrder(request, userId));

    }

}

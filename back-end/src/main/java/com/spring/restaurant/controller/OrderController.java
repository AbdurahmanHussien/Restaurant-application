package com.spring.restaurant.controller;


import com.spring.restaurant.dto.AllSystemOrderDto;
import com.spring.restaurant.dto.OrderDto;
import com.spring.restaurant.request.OrderRequest;
import com.spring.restaurant.service.auth.CustomUserDetails;
import com.spring.restaurant.service.impl.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;



    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "get all orders")
    public ResponseEntity<Page<AllSystemOrderDto>> getAllOrders(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(orderService.getAllOrders(page, size));
    }

    @PostMapping
    @Operation(summary = "create order")
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderRequest request , @AuthenticationPrincipal UserDetails user) {
        Long userId = ((CustomUserDetails) user).getId();
        request.setUserId(userId);
        return ResponseEntity.ok(orderService.createOrder(request, userId));

    }

    @GetMapping("/{id}")
    @Operation(summary = "get order by id")
    public ResponseEntity<Page<OrderDto>> getOrderById(@PathVariable Long id, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(orderService.getUserOrders(id, page, size));
    }
}

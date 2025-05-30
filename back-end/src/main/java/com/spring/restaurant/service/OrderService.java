package com.spring.restaurant.service;

import com.spring.restaurant.dto.OrderItemDto;
import com.spring.restaurant.entity.OrderItem;
import com.spring.restaurant.entity.Product;
import com.spring.restaurant.entity.auth.User;
import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.mapper.OrderMapper;
import com.spring.restaurant.repository.OrderItemRepository;
import com.spring.restaurant.repository.ProductRepository;
import com.spring.restaurant.repository.UserRepository;
import com.spring.restaurant.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;



    public OrderItemDto createOrder(OrderRequest request, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Product> products = productRepository.findByIdIn(request.getProductIds());

        if (products.size() != request.getProductIds().size()) {
            throw new ResourceNotFoundException("product.notfound");
        }
        OrderItem orderItem = OrderItem.builder()
                .code(UUID.randomUUID().toString())
                .totalPrice(request.getTotalPrice())
                .totalQuantity(request.getTotalQuantity())
                .products(products)
                .user(user)
                .build();

        orderItemRepository.save(orderItem);

        return OrderMapper.INSTANCE.orderItemToOrderItemDto(orderItem);
    }
}

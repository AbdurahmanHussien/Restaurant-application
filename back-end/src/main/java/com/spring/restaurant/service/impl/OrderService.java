package com.spring.restaurant.service.impl;

import com.spring.restaurant.dto.OrderItemDto;
import com.spring.restaurant.entity.OrderItem;
import com.spring.restaurant.entity.Product;
import com.spring.restaurant.entity.auth.User;
import com.spring.restaurant.exceptions.BadRequestException;
import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.mapper.OrderMapper;
import com.spring.restaurant.repository.OrderItemRepository;
import com.spring.restaurant.repository.ProductRepository;
import com.spring.restaurant.repository.auth.UserRepository;
import com.spring.restaurant.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;



    public OrderItemDto createOrder(OrderRequest request, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user.notfound"));

        List<Product> products = productRepository.findByIdIn(request.getProductIds());


        String code = UUID.randomUUID().toString().substring(0, 5);
        if (products.size() != request.getProductIds().size()) {
            throw new ResourceNotFoundException("product.notfound");
        }
        OrderItem orderItem = OrderItem.builder()
                .code(code)
                .totalPrice(request.getTotalPrice())
                .totalQuantity(request.getTotalQuantity())
                .products(products)
                .user(user)
                .build();

            orderItemRepository.save(orderItem);

        return OrderMapper.INSTANCE.orderItemToOrderItemDto(orderItem);
    }


    public List<OrderItemDto> getAllOrders(int page, int size) {
        if(page <= 0){
            throw new BadRequestException("page.zero");
        }
        Pageable pageable = PageRequest.of(page - 1, size);

        return orderItemRepository.findAll(pageable)
                .stream()
                .map(OrderMapper.INSTANCE::orderItemToOrderItemDto)
                .collect(Collectors.toList());
    }

    public List<OrderItemDto> getUserOrders(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user.notfound"));
       List<OrderItemDto> orderItem= orderItemRepository.findAllByUserId(userId).stream().map(OrderMapper.INSTANCE::orderItemToOrderItemDto).toList();
       if (orderItem.isEmpty()) {
           throw new ResourceNotFoundException("order.notfound");
       }
        return orderItem;
    }
}

package com.spring.restaurant.service.impl;

import com.spring.restaurant.dto.AllSystemOrderDto;
import com.spring.restaurant.dto.OrderDto;
import com.spring.restaurant.dto.OrderProductDTO;
import com.spring.restaurant.entity.Order;
import com.spring.restaurant.entity.OrderItem;
import com.spring.restaurant.entity.Product;
import com.spring.restaurant.entity.auth.User;
import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.mapper.OrderMapper;
import com.spring.restaurant.repository.OrderRepository;
import com.spring.restaurant.repository.ProductRepository;
import com.spring.restaurant.repository.auth.UserRepository;
import com.spring.restaurant.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;



    @CacheEvict(value = {"orders", "userOrders"}, allEntries = true)
    public OrderDto createOrder(OrderRequest request, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user.notfound"));

        List<Long> productIds = request.getProducts().stream()
                .map(OrderProductDTO::getProductId)
                .collect(Collectors.toList());

        List<Product> products = productRepository.findByIdIn(productIds);

        if (products.size() != request.getProducts().size()) {
            throw new ResourceNotFoundException("product.notfound");
        }

        Map<Long, OrderProductDTO> productInfoMap = request.getProducts().stream()
                .collect(Collectors.toMap(OrderProductDTO::getProductId, dto -> dto));

        String code = UUID.randomUUID().toString().substring(0, 5);

        Order order = Order.builder()
                .code(code)
                .totalPrice(request.getTotalPrice())
                .totalQuantity(request.getTotalQuantity())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        List<OrderItem> items = products.stream()
                .map(product -> {
                    OrderProductDTO info = productInfoMap.get(product.getId());
                    return OrderItem.builder()
                            .order(order)
                            .product(product)
                            .quantity(info.getQuantity())
                            .price(info.getPrice())
                            .build();
                })
                .collect(Collectors.toList());
        order.setOrderItems(items);

        orderRepository.save(order);

        return OrderMapper.INSTANCE.orderToOrderDto(order);
    }


    @Cacheable(value = "orders", key = "#page + '_' + #size")
    public Page<AllSystemOrderDto> getAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size ,Sort.by("createdAt").descending());
        Page<Order> orders = orderRepository.findAll(pageable);
        return getSystemOrderDtos(orders);
    }

    @Cacheable(value = "userOrders", key = "#userId + '_' + #page + '_' + #size")
    public Page<OrderDto> getUserOrders(Long userId, int page, int size) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdAt").descending());
        Page<Order> orders = orderRepository.findAllByUserId(userId, pageable);

        return getOrderDtos(orders);
    }

    private Page<OrderDto> getOrderDtos(Page<Order> orders) {
        return orders.map(order -> {
            OrderDto dto = OrderMapper.INSTANCE.orderToOrderDto(order);

            List<OrderProductDTO> itemDtos = order.getOrderItems().stream().map(item -> {
                OrderProductDTO itemDto = new OrderProductDTO();
                itemDto.setProductName(item.getProduct().getName());
                itemDto.setQuantity(item.getQuantity());
                itemDto.setPrice((long) item.getPrice());
                return itemDto;
            }).collect(Collectors.toList());

            dto.setItems(itemDtos);

            return dto;
        });
    }
    private Page<AllSystemOrderDto> getSystemOrderDtos(Page<Order> orders) {
        return orders.map(order -> {

            List<OrderProductDTO> itemDtos = order.getOrderItems().stream().map(item ->
                    OrderProductDTO.builder()
                            .productName(item.getProduct().getName())
                            .quantity(item.getQuantity())
                            .price((long) item.getPrice())
                            .build()
            ).collect(Collectors.toList());

            return AllSystemOrderDto.builder()
                    .id(order.getId())
                    .totalPrice(order.getTotalPrice())
                    .totalQuantity(order.getTotalQuantity())
                    .userName(order.getUser().getUsername())
                    .code(order.getCode())
                    .createdAt(order.getCreatedAt())
                    .items(itemDtos)
                    .build();
        });
    }

}

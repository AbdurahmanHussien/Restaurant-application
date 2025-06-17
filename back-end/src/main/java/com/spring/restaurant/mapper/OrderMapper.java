package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.OrderDto;
import com.spring.restaurant.dto.OrderProductDTO;
import com.spring.restaurant.entity.Order;
import com.spring.restaurant.entity.OrderItem;
import com.spring.restaurant.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(uses = ProductMapper.class)
public interface OrderMapper {


    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "user.id", target = "userId")
    OrderDto orderToOrderDto(Order order);


    Order orderDtoToOrder(OrderDto orderDto);


    List<OrderProductDTO> orderItemsToOrderItemDtos(List<OrderItem> items);

    default List<String> mapProductNames(List<Product> products) {
        return products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
    }

}

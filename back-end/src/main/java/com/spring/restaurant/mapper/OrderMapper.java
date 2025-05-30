package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.OrderItemDto;
import com.spring.restaurant.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(uses = ProductMapper.class)
public interface OrderMapper {


    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "user.id", target = "userId")
    OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);


    OrderItem orderItemDtoToOrderItem(OrderItemDto orderItemDto);
}

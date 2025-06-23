package com.spring.restaurant.mapper.auth;

import com.spring.restaurant.dto.authDto.UserDto;
import com.spring.restaurant.entity.auth.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

    User toEntity(UserDto dto);
}

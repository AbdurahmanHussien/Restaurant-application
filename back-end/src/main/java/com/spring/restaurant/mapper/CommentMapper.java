package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.CommentDto;
import com.spring.restaurant.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "contactInfo.id", target = "contactInfoId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    CommentDto toDto(Comment comment);

    @Mapping(source = "contactInfoId", target = "contactInfo.id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "username", target = "user.username")
    Comment toEntity(CommentDto dto);
}

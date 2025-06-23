package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.ContactInfoDto;
import com.spring.restaurant.entity.ContactInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface ContactInfoMapper {

    ContactInfoMapper CONTACT_INFO_MAPPER = Mappers.getMapper(ContactInfoMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "comments", target = "comments")
    ContactInfoDto contactToContactDTO(ContactInfo contactInfo);


    @Mapping(source = "userId", target = "user.id")
    ContactInfo contactDTOToContact(ContactInfoDto dto);

}

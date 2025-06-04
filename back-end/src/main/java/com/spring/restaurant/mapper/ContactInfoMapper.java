package com.spring.restaurant.mapper;
import com.spring.restaurant.dto.ContactInfoDto;
import com.spring.restaurant.entity.ContactInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactInfoMapper {

    ContactInfoMapper CONTACT_INFO_MAPPER = Mappers.getMapper(ContactInfoMapper.class);



    ContactInfoDto contactToContactDTO(ContactInfo contactInfo);


    @Mapping(target = "user.id", source = "userId")
    ContactInfo contactDTOToContact(ContactInfoDto dto);

}

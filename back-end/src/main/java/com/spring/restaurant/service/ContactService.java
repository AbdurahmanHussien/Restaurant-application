package com.spring.restaurant.service;

import com.spring.restaurant.dto.ContactInfoDto;
import com.spring.restaurant.entity.ContactInfo;
import com.spring.restaurant.exceptions.BadRequestException;
import com.spring.restaurant.mapper.ContactInfoMapper;
import com.spring.restaurant.repository.ContactInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class ContactService implements IContactService {

    private ContactInfoRepository contactInfoRepository;

    public ContactService(ContactInfoRepository contactInfoRepository) {
        this.contactInfoRepository = contactInfoRepository;
    }


    @Override
    public ContactInfoDto addInfo(ContactInfoDto contactInfoDto) {
        if (contactInfoDto.getId() != null) {
            throw new BadRequestException("id.notempty");
        }

        var mapper = ContactInfoMapper.CONTACT_INFO_MAPPER;
        ContactInfo contactInfo = mapper.contactDTOToContact(contactInfoDto);
        ContactInfo saved = contactInfoRepository.save(contactInfo);
        return mapper.contactToContactDTO(saved);
    }

}

package com.spring.restaurant.service.impl;

import com.spring.restaurant.dto.ContactInfoDto;
import com.spring.restaurant.entity.ContactInfo;
import com.spring.restaurant.exceptions.BadRequestException;
import com.spring.restaurant.mapper.ContactInfoMapper;
import com.spring.restaurant.repository.ContactInfoRepository;
import com.spring.restaurant.service.IContactService;
import org.springframework.stereotype.Service;



@Service
public class ContactService implements IContactService {

    private final ContactInfoRepository contactInfoRepository;

    public ContactService(ContactInfoRepository contactInfoRepository) {
        this.contactInfoRepository = contactInfoRepository;
    }


    @Override
    public ContactInfoDto addInfo(ContactInfoDto contactInfoDto) {
        if (contactInfoDto.getId() != null) {
            throw new BadRequestException("id.notempty");
        }
        ContactInfo contactInfo = ContactInfoMapper.CONTACT_INFO_MAPPER.contactDTOToContact(contactInfoDto);
        ContactInfo saved = contactInfoRepository.save(contactInfo);
        return ContactInfoMapper.CONTACT_INFO_MAPPER.contactToContactDTO(saved);

    }

}

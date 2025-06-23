package com.spring.restaurant.service.impl;

import com.spring.restaurant.dto.ContactInfoDto;
import com.spring.restaurant.entity.ContactInfo;
import com.spring.restaurant.exceptions.BadRequestException;
import com.spring.restaurant.mapper.ContactInfoMapper;
import com.spring.restaurant.repository.ContactInfoRepository;
import com.spring.restaurant.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ContactService implements IContactService {

    private final ContactInfoRepository contactInfoRepository;

    private final ContactInfoMapper contactInfoMapper;



    @Override
    public ContactInfoDto addInfo(ContactInfoDto contactInfoDto) {
        if (contactInfoDto.getId() != null) {
            throw new BadRequestException("id.notempty");
        }
        ContactInfo contactInfo = contactInfoMapper.contactDTOToContact(contactInfoDto);
        ContactInfo saved = contactInfoRepository.save(contactInfo);
        return contactInfoMapper.contactToContactDTO(saved);

    }

    @Override
    public List<ContactInfoDto> getAllInfo() {
        List<ContactInfo> contactInfos = contactInfoRepository.findAll();
        return contactInfos.stream().map(contactInfoMapper::contactToContactDTO).collect(Collectors.toList());
    }

    @Override
    public List<ContactInfoDto> getAllInfoByUserId(Long userId) {
        List<ContactInfo> contactInfos = contactInfoRepository.findByUserId(userId);
        return contactInfos.stream().map(contactInfoMapper::contactToContactDTO).collect(Collectors.toList());
    }

}

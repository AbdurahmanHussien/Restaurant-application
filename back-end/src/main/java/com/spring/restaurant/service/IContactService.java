package com.spring.restaurant.service;

import com.spring.restaurant.dto.ContactInfoDto;

import java.util.List;


public interface IContactService {


        ContactInfoDto addInfo(ContactInfoDto contactInfoDto);
        List<ContactInfoDto> getAllInfo();
        List<ContactInfoDto> getAllInfoByUserId(Long userId);



}

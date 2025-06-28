package com.spring.restaurant.service.impl;

import com.spring.restaurant.entity.auth.User;
import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.repository.auth.UserRepository;
import com.spring.restaurant.response.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


   @Cacheable(value = "userData", key = "#id")
  public UserData getUserData(long id){
      User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
      return UserData.builder()
              .id(user.getId())
              .username(user.getUsername())
              .name(user.getUserDetails().getName())
              .email(user.getUserDetails().getEmail())
              .phone(user.getUserDetails().getPhoneNum())
              .address(user.getUserDetails().getAddress())
              .age(user.getUserDetails().getAge())
              .build();
    }

    @CachePut(value = "userData", key = "#id")
    public UserData updateUserData(long id, UserData userData) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user.not.found"));
        user.getUserDetails().setName(userData.getName());
        user.getUserDetails().setEmail(userData.getEmail());
        user.getUserDetails().setPhoneNum(userData.getPhone());
        user.getUserDetails().setAddress(userData.getAddress());
        user.getUserDetails().setAge(userData.getAge());
        user.setUsername(userData.getUsername());
        User updatedUser = userRepository.save(user);

        return UserData.builder()
                .id(updatedUser.getId())
                .username(updatedUser.getUsername())
                .name(updatedUser.getUserDetails().getName())
                .email(updatedUser.getUserDetails().getEmail())
                .phone(updatedUser.getUserDetails().getPhoneNum())
                .address(updatedUser.getUserDetails().getAddress())
                .age(updatedUser.getUserDetails().getAge())
                .build();
    }

}

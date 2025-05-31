package com.spring.restaurant.dto.authDto;

import com.spring.restaurant.entity.auth.User;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsDto {


    private Long id;

    private String name;

    private String phoneNum;

    @Email
    private String email;

    private String address;

    private User user;



}

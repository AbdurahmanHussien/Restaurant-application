package com.spring.restaurant.dto.authDto;
import com.spring.restaurant.entity.ContactInfo;
import com.spring.restaurant.entity.auth.Role;
import com.spring.restaurant.entity.auth.UserDetails;
import lombok.*;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;


    private String username;

    private String password;

    private UserDetails userDetails;

    List<Role> roles ;


    List<ContactInfo> contactInfos ;




}

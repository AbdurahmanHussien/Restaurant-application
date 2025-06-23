package com.spring.restaurant.dto.authDto;

import com.spring.restaurant.dto.CommentDto;
import com.spring.restaurant.entity.ContactInfo;
import com.spring.restaurant.entity.Order;
import com.spring.restaurant.entity.auth.Role;
import com.spring.restaurant.entity.auth.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
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

    List<Order> orderItems;

    List<CommentDto> comments;




}

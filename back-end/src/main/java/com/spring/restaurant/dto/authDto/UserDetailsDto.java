package com.spring.restaurant.dto.authDto;
import com.spring.restaurant.entity.auth.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsDto {


    private Long id;

    private String name;

    private String phoneNum;

    private String address;

    private User user;



}

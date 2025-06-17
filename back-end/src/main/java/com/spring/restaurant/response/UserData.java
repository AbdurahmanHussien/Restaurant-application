package com.spring.restaurant.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class UserData {

    private Long id;
    private String username;
    private String name;
    private String email;
    private String phone;
    private String address;
    private int age;
}

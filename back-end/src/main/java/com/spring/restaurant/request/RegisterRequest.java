package com.spring.restaurant.request;

import lombok.Builder;

@Builder
public record RegisterRequest( String name, String username, String password,
                               String phoneNum,
                               String email,
                               int age,
                               String address)
{}

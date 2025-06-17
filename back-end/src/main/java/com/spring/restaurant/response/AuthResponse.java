package com.spring.restaurant.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
@AllArgsConstructor
@Builder
public class AuthResponse {

    private Long userId;
    private String token;
    private List<String> UserRole;
}

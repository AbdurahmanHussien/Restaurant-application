package com.spring.restaurant.service.auth;

import com.spring.restaurant.entity.auth.User;
import com.spring.restaurant.request.LoginRequest;
import com.spring.restaurant.request.RegisterRequest;
import com.spring.restaurant.response.AuthResponse;

public interface IAuthenticationService {

    AuthResponse createUser(RegisterRequest user);

    AuthResponse loginUser(LoginRequest user);

    User getUserById(Long id);

   void resetPassword(String email);
}

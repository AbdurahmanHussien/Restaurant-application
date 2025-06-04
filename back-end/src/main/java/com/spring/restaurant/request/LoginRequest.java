package com.spring.restaurant.request;
import lombok.*;

@Builder
public record LoginRequest(  String email, String password) {

}

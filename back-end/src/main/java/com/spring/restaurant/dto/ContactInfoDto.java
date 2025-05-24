package com.spring.restaurant.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactInfoDto {


    private Long id;

    @NotEmpty
    private String name;

    @Email(message = "email.valid")
    private String email;

    private String subject;

    @Size(min = 20 , message = "short.message")
    private String message;


    private Long UserId;



}

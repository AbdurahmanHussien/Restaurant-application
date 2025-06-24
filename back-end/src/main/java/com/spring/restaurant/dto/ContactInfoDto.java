package com.spring.restaurant.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactInfoDto implements Serializable {


    private Long id;

    @NotEmpty
    private String name;

    @Email(message = "email.valid")
    private String email;

    private String subject;

    @Size(min = 20 , message = "short.message")
    private String message;


    private Long userId;

    private List<CommentDto> comments;



}

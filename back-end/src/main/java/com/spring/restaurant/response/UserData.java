package com.spring.restaurant.response;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter @Setter
public class UserData implements Serializable {

    private Long id;

    @NotBlank(message = "user.username.notblank")  @Size(min = 5, max = 10, message = "user.username.size")
    @Pattern(regexp = "^\\S+$", message = "user.name.pattern")
    private String username;

    @NotBlank(message = "user.name.notblank")
    private String name;

    @NotBlank(message = "email.notblank")
    @Email(message = "email.valid")
    private String email;

    @NotBlank(message = "phoneNum.notblank")
    @Pattern(
            regexp = "^01[0-2,5]{1}[0-9]{8}$",
            message = "egypt.phoneNum.pattern"
    )
    private String phone;

    @NotBlank(message = "address.notblank")
    @Pattern(regexp = ".*\\b[Ee]gypt\\b$", message = "address.pattern")
    private String address;

    @Min(value = 16, message = "Min.age") @Max(value = 80, message = "max.age")
    private int age;
}

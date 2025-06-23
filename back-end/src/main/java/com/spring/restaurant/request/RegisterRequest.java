package com.spring.restaurant.request;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record RegisterRequest(  @NotBlank(message = "user.name.notblank")
                                String name,
                                @NotBlank(message = "user.username.notblank")  @Size(min = 7, max = 10, message = "user.username.size")
                                @Pattern(regexp = "^\\S+$", message = "user.name.pattern")
                               String username,

                                @NotBlank(message = "password.notblank")
                                @Size(min = 8, max = 20, message = "password.size")
                                @Pattern(
                                        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                                        message = "password.pattern"
                                )
                               String password,
                                @NotBlank(message = "phoneNum.notblank")
                                @Pattern(
                                        regexp = "^01[0-2,5]{1}[0-9]{8}$",
                                        message = "egypt.phoneNum.pattern"
                                )
                               String phoneNum,
                                @NotBlank(message = "email.notblank")
                                @Email(message = "email.valid")
                               String email,
                               @Min(value = 16, message = "Min.age") @Max(value = 80, message = "max.age")
                               int age,
                               @NotBlank(message = "address.notblank")
                               @Pattern(regexp = ".*\\b[Ee]gypt\\b$", message = "address.pattern")
                               String address
)
{}

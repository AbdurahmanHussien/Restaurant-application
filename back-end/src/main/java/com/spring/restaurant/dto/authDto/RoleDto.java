package com.spring.restaurant.dto.authDto;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {


        private Long id;


        @Enumerated(EnumType.STRING)
        private String roleName;





}

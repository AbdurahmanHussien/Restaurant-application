package com.spring.restaurant.dto.authDto;
import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {


        private Long id;

        @Enumerated(EnumType.STRING)
        private String roleName;





}

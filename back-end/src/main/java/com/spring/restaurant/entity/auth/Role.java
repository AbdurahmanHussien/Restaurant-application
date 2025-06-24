package com.spring.restaurant.entity.auth;

import com.spring.restaurant.utils.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
@Builder
public class Role implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "role_type")
        @Enumerated(EnumType.STRING)
        private RoleType roleType;



         @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
         private Set<User> users = new HashSet<>();



}

package com.spring.restaurant.entity.auth;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNum;

    @Column(unique = true)
    private String email;

    private String address;

    private int age;

    @OneToOne(mappedBy = "userDetails")
    private User user;



}

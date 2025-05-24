package com.spring.restaurant.entity;


import com.spring.restaurant.entity.auth.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ContactInfo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String subject;

    private String message;


    @ManyToOne
    private User user;




}

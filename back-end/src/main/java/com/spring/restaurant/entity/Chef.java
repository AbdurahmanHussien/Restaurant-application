package com.spring.restaurant.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Chef")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chef {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String specification ;

    private String imagePath;

    private String facebookLink;

    private String twitterLink;

    private String instagramLink;



}

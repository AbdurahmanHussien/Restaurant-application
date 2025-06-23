package com.spring.restaurant.entity.auth;

import com.spring.restaurant.entity.Comment;
import com.spring.restaurant.entity.ContactInfo;
import com.spring.restaurant.entity.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Setter
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName" , unique = true)
    private String username;

    private String password;


    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
     @JoinTable(
             name = "user_roles",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id")
     )
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL , fetch = FetchType.LAZY)
   private List<ContactInfo> contactInfos ;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            orphanRemoval = true , fetch = FetchType.LAZY )
    private List<Order> order;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            orphanRemoval = true , fetch = FetchType.LAZY )
    private List<Comment> comments;


}

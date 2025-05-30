package com.spring.restaurant.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Category")
public class Category {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


     private String name;


     private String highlight;

    private String icon;

   @Builder.Default
   @OneToMany(mappedBy = "category", cascade = CascadeType.ALL , fetch = FetchType.LAZY )
   @JsonIgnoreProperties("category")
   private Set<Product> products = new HashSet<>();

}

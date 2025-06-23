package com.spring.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Product extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;


    private  String name;


    private String imagePath;


    private String description;

    private int price;

   @OneToMany(mappedBy = "product", cascade = CascadeType.ALL , orphanRemoval = true)
    List<OrderItem> orderItems;


    @ManyToOne(fetch = FetchType.LAZY )
    @JsonIgnoreProperties("products")
    private Category category;

}

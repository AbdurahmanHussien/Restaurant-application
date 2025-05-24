package com.spring.restaurant.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;


    private  String name;


    private String imagePath;


    private String description;

    private int price;



    @ManyToOne(fetch = FetchType.LAZY )
    @JsonIgnoreProperties("products")
    private Category category;

}

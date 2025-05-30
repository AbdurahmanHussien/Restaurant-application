package com.spring.restaurant.dto;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChefDto {


    private Long id;

    private String name;

    private String specification ;

    private String imagePath;

    private String facebookLink;

    private String twitterLink;

    private String instagramLink;



}

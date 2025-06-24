package com.spring.restaurant.dto;
import lombok.*;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChefDto implements Serializable {


    private Long id;

    private String name;

    private String specification ;

    private String imagePath;

    private String facebookLink;

    private String twitterLink;

    private String instagramLink;



}

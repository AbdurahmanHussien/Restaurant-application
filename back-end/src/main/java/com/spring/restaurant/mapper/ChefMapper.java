package com.spring.restaurant.mapper;
import com.spring.restaurant.dto.CategoryDto;
import com.spring.restaurant.dto.ChefDto;
import com.spring.restaurant.entity.Chef;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper
public interface ChefMapper {

    ChefMapper CHEF_MAPPER = Mappers.getMapper(ChefMapper.class);


    ChefDto chefToChefDTO(Chef chef);



    Chef chefDTOToChef(ChefDto dto);

}

package com.spring.restaurant.service.impl;
import com.spring.restaurant.dto.ChefDto;
import com.spring.restaurant.mapper.ChefMapper;
import com.spring.restaurant.repository.ChefRepository;
import com.spring.restaurant.service.IChefService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ChefService implements IChefService {


    private final ChefRepository chefRepository;
    public ChefService(ChefRepository chefRepository) {
        this.chefRepository = chefRepository;
    }


    @Override
    @Cacheable( value = "chefs", key= "'all'")
    public List<ChefDto> getAllChefs() {
         return chefRepository.findAll().stream().map(ChefMapper.CHEF_MAPPER::chefToChefDTO).collect(Collectors.toList());
    }
}

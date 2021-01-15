package com.restaurant.foodonline.service.impl;


import com.restaurant.foodonline.dto.RestaurantDto;
import com.restaurant.foodonline.entity.RestaurantEntity;
import com.restaurant.foodonline.enums.UserRoleEnum;
import com.restaurant.foodonline.repository.RestaurantRepository;
import com.restaurant.foodonline.repository.UserRepository;
import com.restaurant.foodonline.service.RestaurantService;
import com.restaurant.foodonline.util.RandomUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RestaurantServiceImp implements RestaurantService {

    Logger logger = LoggerFactory.getLogger(RestaurantServiceImp.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RandomUtil randomUtil;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public RestaurantDto register(RestaurantDto restaurantDto) {

        if (userRepository.findByUsername(restaurantDto.getUsername()) != null) {
            String msg = "Username is duplicate.";
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
        }

        RestaurantEntity restaurantEntity = modelMapper.map(restaurantDto, RestaurantEntity.class);
        if (restaurantRepository.findByRestaurantName(restaurantEntity.getRestaurantName()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Restaurant name is duplicate.");
        }

        restaurantEntity.setRestaurantId(randomUtil.generateUniqueId());
        restaurantEntity.setRole(UserRoleEnum.RESTAURANT);
        restaurantEntity.setUserId(randomUtil.generateUniqueId());
        restaurantEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(restaurantDto.getPassword()));

        RestaurantEntity restaurantStored = restaurantRepository.save(restaurantEntity);
        return modelMapper.map(restaurantStored, RestaurantDto.class);

    }

    @Override
    public RestaurantDto getByRestaurantId(String restaurantId) {
        RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(restaurantId);
        if (restaurantEntity == null) {
            String msg = String.format("Restaurant with restaurantId %s not found.", restaurantId);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }

        return modelMapper.map(restaurantEntity, RestaurantDto.class);
    }

    @Override
    public boolean deleteRestaurantRestaurantId(String restaurantId) {
        RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(restaurantId);
        if (restaurantEntity == null) {
            String msg = String.format("Restaurant with restaurantNumber %s not found.", restaurantId);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }

        restaurantRepository.delete(restaurantEntity);
        return true;
    }

    @Override
    public RestaurantDto updateRestaurantInfo(String restaurantId, RestaurantDto restaurantDto) {
        RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(restaurantId);
        if (restaurantEntity == null) {
            String msg = String.format("Restaurant with restaurantId %s not found.", restaurantId);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }

        restaurantEntity.setRestaurantName(restaurantDto.getRestaurantName());
        restaurantEntity.setDeliveryCost(restaurantDto.getDeliveryCost());
        restaurantEntity.setTel(restaurantDto.getTel());
        restaurantEntity.setAddress(restaurantDto.getAddress());
        restaurantEntity.setRestaurantCategory(restaurantDto.getRestaurantCategory());

        RestaurantEntity restaurantStored = restaurantRepository.save(restaurantEntity);
        return modelMapper.map(restaurantStored, RestaurantDto.class);
    }

    @Override
    public List<RestaurantDto> restaurantList() {
        List<RestaurantEntity> restaurantEntityList = restaurantRepository.findAll();

        List<RestaurantDto> restaurantDtoList
                = modelMapper.map(restaurantEntityList, new TypeToken<List<RestaurantDto>>() {}.getType());

        return restaurantDtoList;
    }


}

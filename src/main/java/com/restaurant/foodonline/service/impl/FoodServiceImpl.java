package com.restaurant.foodonline.service.impl;


import com.restaurant.foodonline.dto.FoodDto;
import com.restaurant.foodonline.entity.FoodEntity;
import com.restaurant.foodonline.entity.RestaurantEntity;
import com.restaurant.foodonline.repository.FoodRepository;
import com.restaurant.foodonline.repository.RestaurantRepository;
import com.restaurant.foodonline.service.FoodService;
import com.restaurant.foodonline.util.RandomUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class FoodServiceImpl implements FoodService {
    Logger logger = LoggerFactory.getLogger(FoodServiceImpl.class);
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    RandomUtil randomUtil;


    @Override
    public FoodDto addFood(String restaurantId, FoodDto foodDto) {

        RestaurantEntity restaurantStored = restaurantRepository.findByRestaurantId(restaurantId);
        if (restaurantStored == null) {
            String msg = String.format("Restaurant with restaurantId %s not found.", restaurantId);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }
        FoodEntity foodStored = foodRepository.findByNameAndRestaurantEntity(foodDto.getName(), restaurantStored);
        if (foodStored != null) {
            String msg = String.format("Food with food name %s is duplicate.", foodDto.getName());
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }
        FoodEntity foodEntity = modelMapper.map(foodDto, FoodEntity.class);
        foodEntity.setFoodId(randomUtil.generateUniqueId());
        foodEntity.setRestaurantEntity(restaurantStored);

        foodStored = foodRepository.save(foodEntity);
        FoodDto returnValue = modelMapper.map(foodStored, FoodDto.class);
        return returnValue;

    }

    @Override
    public FoodDto findByFoodId(String foodId) {

        FoodEntity foodStored = foodRepository.findByFoodId(foodId);
        if (foodStored == null) {
            String msg = String.format("Food with foodId %s not found.", foodId);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }
        FoodDto returnValue = modelMapper.map(foodStored, FoodDto.class);
        return returnValue;
    }

    @Override
    public boolean deleteByFoodId(String foodId) {
        FoodEntity foodStored = foodRepository.findByFoodId(foodId);
        if (foodStored == null) {
            String msg = String.format("Food with foodId %s not found.", foodId);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }
        foodRepository.delete(foodStored);
        return true;

    }

    @Override
    public FoodDto updateFood(String foodId, FoodDto foodDto) {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        if (foodEntity == null) {
            String msg = String.format("Food with foodId %s not found.", foodId);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }

        foodEntity.setName(foodDto.getName());
        foodEntity.setPrice(foodDto.getPrice());

        FoodEntity foodStored = foodRepository.save(foodEntity);
        return modelMapper.map(foodStored, FoodDto.class);
    }

    @Override
    public List<FoodDto> foodList(String restaurantId) {
        RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(restaurantId);
        if (restaurantEntity == null) {
            String msg = String.format("Restaurant with restaurantId %s not found.", restaurantId);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }


        List<FoodEntity> foodEntityList = new ArrayList<>();
        for (FoodEntity foodEntity : restaurantEntity.getFoodList()) {
            foodEntityList.add(foodEntity);
        }
        List<FoodDto> foodDtoList
                = modelMapper.map(foodEntityList, new TypeToken<List<FoodDto>>() {
        }.getType());

        return foodDtoList;
    }
}




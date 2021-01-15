package com.restaurant.foodonline.service;


import com.restaurant.foodonline.dto.FoodDto;

import java.util.List;

public interface FoodService {
    FoodDto addFood(String restaurantId, FoodDto foodDto);

    FoodDto findByFoodId(String foodId);

    boolean deleteByFoodId(String foodId);

    FoodDto updateFood(String foodId, FoodDto foodDto);

    List<FoodDto> foodList(String restaurantId);

}


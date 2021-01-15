package com.restaurant.foodonline.service;


import com.restaurant.foodonline.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    RestaurantDto register(RestaurantDto restaurantDto);

    RestaurantDto getByRestaurantId(String restaurantId);

    boolean deleteRestaurantRestaurantId(String restaurantId);

    RestaurantDto updateRestaurantInfo(String restaurantId, RestaurantDto restaurantDto);

    List<RestaurantDto> restaurantList();
}

package com.restaurant.foodonline.dto;

import com.restaurant.foodonline.enums.CartStatusEnum;

import java.util.HashSet;
import java.util.Set;

public class CartDto {
    private Long id;
    private Long timestamp;
    private String cartNumber;
    private CartStatusEnum cartStatus;
    private UserDto userDto;
    private RestaurantDto restaurantDto;
    private Set<CartFoodDto> cartFoodList = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public RestaurantDto getRestaurantDto() {
        return restaurantDto;
    }

    public void setRestaurantDto(RestaurantDto restaurantDto) {
        this.restaurantDto = restaurantDto;
    }

    public Set<CartFoodDto> getCartFoodList() {
        return cartFoodList;
    }

    public void setCartFoodList(Set<CartFoodDto> cartFoodList) {
        this.cartFoodList = cartFoodList;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
    }

    public CartStatusEnum getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(CartStatusEnum cartStatus) {
        this.cartStatus = cartStatus;
    }
}

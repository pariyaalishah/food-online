package com.restaurant.foodonline.dto;

public class FoodDto {
    private Long id;
    private String foodId;
    private String name;
    private Long price;
    private RestaurantDto restaurantDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public RestaurantDto getRestaurantDto() {
        return restaurantDto;
    }

    public void setRestaurantDto(RestaurantDto restaurantDto) {
        this.restaurantDto = restaurantDto;
    }
}

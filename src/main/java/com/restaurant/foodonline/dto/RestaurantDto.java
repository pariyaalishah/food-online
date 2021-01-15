package com.restaurant.foodonline.dto;

import com.restaurant.foodonline.enums.RestaurantCategoryEnum;

import java.util.HashSet;
import java.util.Set;

public class RestaurantDto extends UserDto{
    private Long id;
    private String restaurantId;
    private String restaurantName;
    private String tel;
    private String deliveryCost;
    private RestaurantCategoryEnum restaurantCategory;
    private Set<FoodDto> foodList = new HashSet<>();

    public Set<FoodDto> getFoodList() {
        return foodList;
    }

    public void setFoodList(Set<FoodDto> foodList) {
        this.foodList = foodList;
    }

    public RestaurantCategoryEnum getRestaurantCategory() {
        return restaurantCategory;
    }

    public void setRestaurantCategory(RestaurantCategoryEnum restaurantCategory) {
        this.restaurantCategory = restaurantCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }


    public String getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(String deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
}

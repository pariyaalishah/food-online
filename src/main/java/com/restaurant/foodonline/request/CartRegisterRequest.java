package com.restaurant.foodonline.request;

import java.util.HashSet;
import java.util.Set;

public class CartRegisterRequest {
    private Long timestamp;
    private String userId;
    private String restaurantId;
    private Set<CartRegisterFoodRequest> cartFoodsList = new HashSet<>();

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Set<CartRegisterFoodRequest> getCartFoodsList() {
        return cartFoodsList;
    }

    public void setCartFoodsList(Set<CartRegisterFoodRequest> cartFoodsList) {
        this.cartFoodsList = cartFoodsList;
    }
}

package com.restaurant.foodonline.response;

import com.restaurant.foodonline.enums.CartStatusEnum;

import java.util.HashSet;
import java.util.Set;

public class CartInfoResponse {
    private String cartNumber;
    private CartStatusEnum cartStatus;
    private String address;
    private String userId;
    private String restaurantId;
    private String restaurantName;
    private Long timestamp;
    private Long totalPrice;
    private Set<CartInfoFoodResponse> cartFoodsList = new HashSet<>();

    public String getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Set<CartInfoFoodResponse> getCartFoodsList() {
        return cartFoodsList;
    }

    public void setCartFoodsList(Set<CartInfoFoodResponse> cartFoodsList) {
        this.cartFoodsList = cartFoodsList;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartStatusEnum getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(CartStatusEnum cartStatus) {
        this.cartStatus = cartStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

package com.restaurant.foodonline.response;

import com.restaurant.foodonline.enums.RestaurantCategoryEnum;

public class RestaurantInfoResponse {
    private String restaurantId;
    private String restaurantName;
    private String tel;
    private String deliveryCost;
    private RestaurantCategoryEnum restaurantCategory;
    private String address;

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(String deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public RestaurantCategoryEnum getRestaurantCategory() {
        return restaurantCategory;
    }

    public void setRestaurantCategory(RestaurantCategoryEnum restaurantCategory) {
        this.restaurantCategory = restaurantCategory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

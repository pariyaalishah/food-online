package com.restaurant.foodonline.request;

import com.restaurant.foodonline.enums.RestaurantCategoryEnum;

public class RestaurantUpdateRequest {
    private String restaurantName;
    private RestaurantCategoryEnum restaurantCategory;
    private String tel;
    private String deliveryCost;
    private String address;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public RestaurantCategoryEnum getRestaurantCategory() {
        return restaurantCategory;
    }

    public void setRestaurantCategory(RestaurantCategoryEnum restaurantCategory) {
        this.restaurantCategory = restaurantCategory;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

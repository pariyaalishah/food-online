package com.restaurant.foodonline.request;

public class CartRegisterFoodRequest {
    private String foodId;
    private int count;

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

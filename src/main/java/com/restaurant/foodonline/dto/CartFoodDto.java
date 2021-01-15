package com.restaurant.foodonline.dto;


public class CartFoodDto {
    private Long id;
    private int count;
    private CartDto cartDto;
    private FoodDto foodDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CartDto getCartDto() {
        return cartDto;
    }

    public void setCartDto(CartDto cartDto) {
        this.cartDto = cartDto;
    }

    public FoodDto getFoodDto() {
        return foodDto;
    }

    public void setFoodDto(FoodDto foodDto) {
        this.foodDto = foodDto;
    }
}

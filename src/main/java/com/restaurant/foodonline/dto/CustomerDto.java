package com.restaurant.foodonline.dto;

import com.restaurant.foodonline.entity.CartEntity;

import java.util.HashSet;
import java.util.Set;

public class CustomerDto extends UserDto{
    private Set<CartEntity> cartList = new HashSet<>();

    public Set<CartEntity> getCartList() {
        return cartList;
    }

    public void setCartList(Set<CartEntity> cartList) {
        this.cartList = cartList;
    }
}

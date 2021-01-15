package com.restaurant.foodonline.request;

import com.restaurant.foodonline.enums.CartStatusEnum;

public class CartStatusRequest {
    private CartStatusEnum cartStatus;

    public CartStatusEnum getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(CartStatusEnum cartStatus) {
        this.cartStatus = cartStatus;
    }
}

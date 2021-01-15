package com.restaurant.foodonline.service;

import com.restaurant.foodonline.enums.CartStatusEnum;
import com.restaurant.foodonline.request.CartRegisterRequest;
import com.restaurant.foodonline.response.CartInfoResponse;

public interface CartService {
    String creatCart (CartRegisterRequest cartReq);
    CartInfoResponse getCartInfo(String cartNumber);
    boolean updateCartStatus(String cartNumber, CartStatusEnum cartStatus);
}

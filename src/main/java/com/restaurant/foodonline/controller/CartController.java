package com.restaurant.foodonline.controller;

import com.restaurant.foodonline.request.CartRegisterRequest;
import com.restaurant.foodonline.request.CartStatusRequest;
import com.restaurant.foodonline.response.CartInfoResponse;
import com.restaurant.foodonline.response.CartRegisterResponse;
import com.restaurant.foodonline.security.AuthenticationUser;
import com.restaurant.foodonline.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    private AuthenticationUser authenticationUser;

    @RolesAllowed({"ROLE_ADMIN","ROLE_RESTAURANT"})
    @PostMapping()
    public CartRegisterResponse create(@RequestBody CartRegisterRequest cartReq) {

        String cartNumber = cartService.creatCart(cartReq);

        CartRegisterResponse cartRegisterResponse = new CartRegisterResponse();
        cartRegisterResponse.setCartNumber(cartNumber);
        return cartRegisterResponse;

    }

    @RolesAllowed({"ROLE_ADMIN","ROLE_CUSTOMER","ROLE_RESTAURANT"})
    @GetMapping("/{cartNumber}")
    public CartInfoResponse getCartInfo(@PathVariable String cartNumber){

        return cartService.getCartInfo(cartNumber);
    }

    @RolesAllowed({"ROLE_ADMIN","ROLE_RESTAURANT"})
    @PutMapping("/{cartNumber}/status")
    public boolean updateCartStatus(@PathVariable String cartNumber
            , @RequestBody CartStatusRequest cartStatusRequest){

        return cartService.updateCartStatus(cartNumber, cartStatusRequest.getCartStatus());
    }

}

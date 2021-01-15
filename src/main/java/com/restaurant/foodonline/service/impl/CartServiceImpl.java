package com.restaurant.foodonline.service.impl;


import com.restaurant.foodonline.entity.*;
import com.restaurant.foodonline.enums.CartStatusEnum;
import com.restaurant.foodonline.repository.*;
import com.restaurant.foodonline.request.CartRegisterFoodRequest;
import com.restaurant.foodonline.request.CartRegisterRequest;
import com.restaurant.foodonline.response.CartInfoFoodResponse;
import com.restaurant.foodonline.response.CartInfoResponse;
import com.restaurant.foodonline.service.CartService;
import com.restaurant.foodonline.util.RandomUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CartServiceImpl implements CartService {
    Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    RandomUtil randomUtil;


    @Override
    public String creatCart(CartRegisterRequest cartReq) {

        CartEntity cartEntity = new CartEntity();
        cartEntity.setTimestamp(cartReq.getTimestamp());

        CustomerEntity customerEntity = customerRepository.findByUserId(cartReq.getUserId());
        if (customerEntity == null) {
            String msg = String.format("User with Id %s not found.", cartReq.getUserId());
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }
        cartEntity.setCustomerEntity(customerEntity);

        RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(cartReq.getRestaurantId());
        if (restaurantEntity == null) {
            String msg = String.format("Restaurant with Id %s not found.", cartReq.getRestaurantId());
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }
        cartEntity.setRestaurantEntity(restaurantEntity);

        for(CartRegisterFoodRequest foodReq : cartReq.getCartFoodsList()){
            CartFoodEntity cartFoodEntity = new CartFoodEntity();

            FoodEntity foodEntity = foodRepository.findByFoodId(foodReq.getFoodId());
            if (foodEntity == null) {
                String msg = String.format("Food with Id %s not found.", foodReq.getFoodId());
                logger.error(msg);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
            }

            cartFoodEntity.setCount(foodReq.getCount());
            cartFoodEntity.setFoodEntity(foodEntity);
            cartFoodEntity.setCartEntity(cartEntity);
            cartEntity.getCartFoodList().add(cartFoodEntity);
        }

        cartEntity.setCartNumber(randomUtil.generateAlphanumeric(10));
        CartEntity cartStored = cartRepository.save(cartEntity);

        return cartStored.getCartNumber();
    }

    @Override
    public CartInfoResponse getCartInfo(String cartNumber) {
        CartEntity cartEntity = cartRepository.findByCartNumber(cartNumber);

        if (cartEntity == null){
            String msg = String.format("Cart with Id %s not found.", cartNumber);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }

        CartInfoResponse cartResponse = new CartInfoResponse();
        cartResponse.setCartNumber(cartNumber);
        cartResponse.setAddress(cartEntity.getCustomerEntity().getAddress());
        cartResponse.setUserId(cartEntity.getCustomerEntity().getUserId());
        cartResponse.setRestaurantId(cartEntity.getRestaurantEntity().getRestaurantId());
        cartResponse.setRestaurantName(cartEntity.getRestaurantEntity().getRestaurantName());
        cartResponse.setTimestamp(cartEntity.getTimestamp());
        cartResponse.setCartStatus(CartStatusEnum.ORDERED);

        Long totalPrice = 0L;
        for(CartFoodEntity cartFoodEntity : cartEntity.getCartFoodList()){
            CartInfoFoodResponse cartFood = new CartInfoFoodResponse();
            cartFood.setFoodId(cartFoodEntity.getFoodEntity().getFoodId());
            cartFood.setName(cartFoodEntity.getFoodEntity().getName());
            cartFood.setPrice(cartFoodEntity.getFoodEntity().getPrice());
            cartFood.setCount(cartFoodEntity.getCount());

            totalPrice = totalPrice + (cartFoodEntity.getFoodEntity().getPrice() * cartFoodEntity.getCount());
            cartResponse.getCartFoodsList().add(cartFood);
        }

        cartResponse.setTotalPrice(totalPrice);

        return cartResponse;
    }

    @Override
    public boolean updateCartStatus(String cartNumber, CartStatusEnum cartStatus) {
        CartEntity cartEntity = cartRepository.findByCartNumber(cartNumber);

        if (cartEntity == null){
            String msg = String.format("Cart with Id %s not found.", cartNumber);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }

        cartEntity.setCartStatus(cartStatus);
        cartRepository.save(cartEntity);

        return true;
    }


}

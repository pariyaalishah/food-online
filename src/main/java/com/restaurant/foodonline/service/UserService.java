package com.restaurant.foodonline.service;


import com.restaurant.foodonline.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    UserDto updateUserInfo(String userId, UserDto userDto);
    UserDto getUserByUsername(String username);
    UserDto getUserByUserId(String userId);
}

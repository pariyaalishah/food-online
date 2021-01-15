package com.restaurant.foodonline.controller;

import com.restaurant.foodonline.dto.UserDto;
import com.restaurant.foodonline.request.UserUpdateRequest;
import com.restaurant.foodonline.response.UserUpdateResponse;
import com.restaurant.foodonline.security.AuthenticationUser;
import com.restaurant.foodonline.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("user")
public class UserController {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationUser authenticationUser;

    @RolesAllowed({"ROLE_ADMIN","ROLE_CUSTOMER","ROLE_RESTAURANT"})
    @PutMapping()
    public UserUpdateResponse updateUser(@RequestBody UserUpdateRequest userRequest) {
        String userId = authenticationUser.getAuthentication().getName();

        UserDto userDto = modelMapper.map(userRequest, UserDto.class);
        UserDto userUpdatedDto = userService.updateUserInfo(userId, userDto);

        return modelMapper.map(userUpdatedDto, UserUpdateResponse.class);
    }
}

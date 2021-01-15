package com.restaurant.foodonline.controller;

import com.restaurant.foodonline.dto.RestaurantDto;
import com.restaurant.foodonline.request.RestaurantRegisterRequest;
import com.restaurant.foodonline.request.RestaurantUpdateRequest;
import com.restaurant.foodonline.response.FoodRegisterResponse;
import com.restaurant.foodonline.response.RestaurantInfoResponse;
import com.restaurant.foodonline.response.RestaurantRegisterResponse;
import com.restaurant.foodonline.response.RestaurantUpdateResponse;
import com.restaurant.foodonline.security.AuthenticationUser;
import com.restaurant.foodonline.service.RestaurantService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;


@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private AuthenticationUser authenticationUser;

    @PostMapping("/register")
    public RestaurantRegisterResponse register(@RequestBody RestaurantRegisterRequest restaurantReg){
        RestaurantDto restaurantDto = modelMapper.map(restaurantReg, RestaurantDto.class);
        RestaurantDto returnValue = restaurantService.register(restaurantDto);

        return modelMapper.map(returnValue,RestaurantRegisterResponse.class);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_RESTAURANT", "ROLE_CUSTOMER"})
    @GetMapping("/{restaurantId}")
    public RestaurantInfoResponse getRestaurant(@PathVariable String restaurantId){
        RestaurantDto restaurantDto = restaurantService.getByRestaurantId(restaurantId);
        return modelMapper.map(restaurantDto, RestaurantInfoResponse.class);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("/{restaurantId}")
    public Boolean deleteRestaurantRestaurantId(@PathVariable String restaurantId){
        return restaurantService.deleteRestaurantRestaurantId(restaurantId);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_RESTAURANT"})
    @PutMapping("/{restaurantId}")
    public RestaurantUpdateResponse updateRestaurantInfo(@PathVariable String restaurantId, @RequestBody RestaurantUpdateRequest restaurantReg){
        RestaurantDto restaurantDto = modelMapper.map(restaurantReg, RestaurantDto.class);

        RestaurantDto restaurantUpdatedDto = restaurantService.updateRestaurantInfo(restaurantId, restaurantDto);

        return modelMapper.map(restaurantUpdatedDto, RestaurantUpdateResponse.class);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    @GetMapping("/list")
    public List<RestaurantInfoResponse> getRestaurantList(){
        List<RestaurantDto> restaurantDtoList = restaurantService.restaurantList();

        List<RestaurantInfoResponse> restaurantResponseList
                = modelMapper.map(restaurantDtoList, new TypeToken<List<RestaurantInfoResponse>>() {}.getType());

        return restaurantResponseList;
    }
}

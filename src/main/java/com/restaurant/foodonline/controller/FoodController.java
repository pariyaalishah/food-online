package com.restaurant.foodonline.controller;

import com.restaurant.foodonline.dto.FoodDto;
import com.restaurant.foodonline.request.FoodRegisterRequest;
import com.restaurant.foodonline.request.FoodUpdateRequest;
import com.restaurant.foodonline.response.FoodRegisterResponse;
import com.restaurant.foodonline.response.FoodUpdateResponse;
import com.restaurant.foodonline.security.AuthenticationUser;
import com.restaurant.foodonline.service.FoodService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    FoodService foodService;

    @Autowired
    private AuthenticationUser authenticationUser;

    @RolesAllowed({"ROLE_ADMIN", "ROLE_RESTAURANT"})
    @PostMapping()
    public FoodRegisterResponse add(@RequestBody FoodRegisterRequest foodReg) {
        FoodDto foodDto = modelMapper.map(foodReg, FoodDto.class);
        FoodDto returnValue = foodService.addFood(foodReg.getRestaurantNumber(), foodDto);

        return modelMapper.map(returnValue, FoodRegisterResponse.class);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_RESTAURANT", "ROLE_CUSTOMER"})
    @GetMapping("/{foodId}")
    public FoodRegisterResponse getFood(@PathVariable String foodId) {
        FoodDto foodDto = foodService.findByFoodId(foodId);
        return modelMapper.map(foodDto, FoodRegisterResponse.class);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_RESTAURANT"})
    @DeleteMapping("/{foodId}")
    public Boolean deleteFoodId(@PathVariable String foodId) {
        return foodService.deleteByFoodId(foodId);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_RESTAURANT"})
    @PutMapping("/{foodId}")
    public FoodUpdateResponse updateFood(@PathVariable String foodId, @RequestBody FoodUpdateRequest foodReg) {
        FoodDto foodDto = modelMapper.map(foodReg, FoodDto.class);
        FoodDto foodUpdatedDto = foodService.updateFood(foodId, foodDto);

        return modelMapper.map(foodUpdatedDto, FoodUpdateResponse.class);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_RESTAURANT", "ROLE_CUSTOMER"})
    @GetMapping("/restaurant/{restaurantId}")
    public List<FoodRegisterResponse> getFoodList(@PathVariable String restaurantId) {
        List<FoodDto> foodDtoList = foodService.foodList(restaurantId);

        List<FoodRegisterResponse> foodResponseList
                = modelMapper.map(foodDtoList, new TypeToken<List<FoodRegisterResponse>>() {
        }.getType());

        return foodResponseList;
    }
}

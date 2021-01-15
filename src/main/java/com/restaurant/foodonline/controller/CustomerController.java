package com.restaurant.foodonline.controller;

import com.restaurant.foodonline.dto.CustomerDto;
import com.restaurant.foodonline.request.CustomerRegisterRequest;
import com.restaurant.foodonline.response.CustomerRegisterResponse;
import com.restaurant.foodonline.security.AuthenticationUser;
import com.restaurant.foodonline.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    CustomerService customerService;

    @Autowired
    private AuthenticationUser authenticationUser;

    @PostMapping("/register")
    public CustomerRegisterResponse register(@RequestBody CustomerRegisterRequest customerReq) {
        CustomerDto customerDto = modelMapper.map(customerReq, CustomerDto.class);
        CustomerDto customerStored = customerService.register(customerDto);

        return modelMapper.map(customerStored, CustomerRegisterResponse.class);
    }

    @RolesAllowed({"ROLE_CUSTOMER"})
    @GetMapping()
    public CustomerRegisterResponse getUser() {
        String userId = authenticationUser.getAuthentication().getName();

        CustomerDto customerDto = customerService.getCustomerByUserId(userId);
        return modelMapper.map(customerDto, CustomerRegisterResponse.class);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("/{userId}")
    public Boolean deleteCustomer(@PathVariable String userId) {
        return customerService.deleteCustomer(userId);
    }

}

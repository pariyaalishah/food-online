package com.restaurant.foodonline.service;

import com.restaurant.foodonline.dto.CustomerDto;

public interface CustomerService {
    CustomerDto register(CustomerDto customerDto);
    CustomerDto getCustomerByUserId(String userId);
    boolean deleteCustomer(String userId);
}

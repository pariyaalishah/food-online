package com.restaurant.foodonline.service.impl;

import com.restaurant.foodonline.dto.CustomerDto;
import com.restaurant.foodonline.entity.CustomerEntity;
import com.restaurant.foodonline.enums.UserRoleEnum;
import com.restaurant.foodonline.repository.CustomerRepository;
import com.restaurant.foodonline.service.CustomerService;
import com.restaurant.foodonline.util.RandomUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerServiceImpl implements CustomerService {
    final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RandomUtil randomUtil;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public CustomerDto register(CustomerDto customerDto) {
        CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
        if (customerRepository.findByUsername(customerEntity.getUsername()) != null) {
            String msg = "Username is duplicate.";
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
        }

        customerEntity.setRole(UserRoleEnum.CUSTOMER);
        customerEntity.setUserId(randomUtil.generateUniqueId());
        customerEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(customerDto.getPassword()));

        CustomerEntity customerStored = customerRepository.save(customerEntity);
        return modelMapper.map(customerStored, CustomerDto.class);
    }

    @Override
    public CustomerDto getCustomerByUserId(String userId) {

        CustomerEntity customerEntity = customerRepository.findByUserId(userId);
        if (customerEntity == null) {
            String msg = String.format("Customer with Id %s not found.", userId);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }

        return modelMapper.map(customerEntity, CustomerDto.class);
    }



    @Override
    public boolean deleteCustomer(String userId) {
        CustomerEntity userEntity = customerRepository.findByUserId(userId);
        if (userEntity == null) {
            String msg = String.format("Customer with Id %s not found.", userId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }

        customerRepository.delete(userEntity);
        return true;
    }
}

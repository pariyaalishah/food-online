package com.restaurant.foodonline.service.impl;


import com.restaurant.foodonline.dto.UserDto;
import com.restaurant.foodonline.entity.UserEntity;
import com.restaurant.foodonline.repository.UserRepository;
import com.restaurant.foodonline.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto updateUserInfo(String userId, UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            String msg = String.format("User with Id %s not found.", userId);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setMobile(userDto.getMobile());
        userEntity.setAddress(userDto.getAddress());

        UserEntity userStored = userRepository.save(userEntity);
        return modelMapper.map(userStored, UserDto.class);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null){
            String msg = String.format("username with name %s not found: ", username);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String userId) {

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null){
            String msg = String.format("UserId with id %s not found: ", userId);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null){
            String msg = String.format("Username with name %s not found.", username);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }

        List<GrantedAuthority> grantedAuthorityList =new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(userEntity.getRole().toString()));

        return new User(userEntity.getUsername(), userEntity.getEncryptedPassword(),
                true,true, true,
                true, grantedAuthorityList);
    }
}

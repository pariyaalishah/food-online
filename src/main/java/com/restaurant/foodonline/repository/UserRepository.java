package com.restaurant.foodonline.repository;

import com.restaurant.foodonline.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity,Long> {

    UserEntity findByUsername(String username);
    UserEntity findByUserId(String userId);
}

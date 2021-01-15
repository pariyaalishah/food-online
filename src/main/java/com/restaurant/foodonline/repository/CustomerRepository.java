package com.restaurant.foodonline.repository;

import com.restaurant.foodonline.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByUserId(String userId);
    CustomerEntity findByUsername(String userName);
}

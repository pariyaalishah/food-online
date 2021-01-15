package com.restaurant.foodonline.repository;

import com.restaurant.foodonline.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    CartEntity findByCartNumber(String cartNumber);
}

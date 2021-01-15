package com.restaurant.foodonline.repository;



import com.restaurant.foodonline.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Long> {
  RestaurantEntity findByRestaurantId(String restaurantId);
    RestaurantEntity findByRestaurantName(String name);

}

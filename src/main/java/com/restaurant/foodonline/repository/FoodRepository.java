package com.restaurant.foodonline.repository;


import com.restaurant.foodonline.entity.FoodEntity;
import com.restaurant.foodonline.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity,Long> {
    FoodEntity findByFoodId(String foodId);
    FoodEntity findByNameAndRestaurantEntity(String name, RestaurantEntity restaurantEntity);

}

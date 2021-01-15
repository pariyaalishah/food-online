package com.restaurant.foodonline.entity;


import com.restaurant.foodonline.enums.RestaurantCategoryEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "restaurant")
public class RestaurantEntity extends UserEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String restaurantId;
    private String restaurantName;
    private String tel;
    private String deliveryCost;

    @Enumerated(EnumType.STRING)
    private RestaurantCategoryEnum restaurantCategory;

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL)
    private Set<FoodEntity> foodList = new HashSet<>();

    public Set<FoodEntity> getFoodList() {
        return foodList;
    }

    public void setFoodList(Set<FoodEntity> foodList) {
        this.foodList = foodList;
    }

    public RestaurantCategoryEnum getRestaurantCategory() {
        return restaurantCategory;
    }

    public void setRestaurantCategory(RestaurantCategoryEnum categoryRestaurant) {
        this.restaurantCategory = categoryRestaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String name) {
        this.restaurantName = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(String deliveryCast) {
        this.deliveryCost = deliveryCast;
    }
}

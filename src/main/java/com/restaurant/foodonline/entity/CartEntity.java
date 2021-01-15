package com.restaurant.foodonline.entity;


import com.restaurant.foodonline.enums.CartStatusEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long timestamp;
    private String cartNumber;

    @Enumerated(EnumType.STRING)
    private CartStatusEnum cartStatus;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private RestaurantEntity restaurantEntity;

    @OneToMany(mappedBy = "cartEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CartFoodEntity> cartFoodList = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public RestaurantEntity getRestaurantEntity() {
        return restaurantEntity;
    }

    public void setRestaurantEntity(RestaurantEntity restaurant) {
        this.restaurantEntity = restaurant;
    }

    public Set<CartFoodEntity> getCartFoodList() {
        return cartFoodList;
    }

    public void setCartFoodList(Set<CartFoodEntity> cartFoodsList) {
        this.cartFoodList = cartFoodsList;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public CartStatusEnum getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(CartStatusEnum cartStatus) {
        this.cartStatus = cartStatus;
    }
}
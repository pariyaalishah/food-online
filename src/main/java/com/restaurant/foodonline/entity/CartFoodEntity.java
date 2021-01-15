package com.restaurant.foodonline.entity;

import javax.persistence.*;

@Entity
@Table(name = "cart_food")
public class CartFoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer count;

    @ManyToOne()
    @JoinColumn(name="cart_id")
    private CartEntity cartEntity;

    @ManyToOne()
    @JoinColumn(name="food_id")
    private FoodEntity foodEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public FoodEntity getFoodEntity() {
        return foodEntity;
    }

    public void setFoodEntity(FoodEntity food) {
        this.foodEntity = food;
    }

    public CartEntity getCartEntity() {
        return cartEntity;
    }

    public void setCartEntity(CartEntity cartEntity) {
        this.cartEntity = cartEntity;
    }

}

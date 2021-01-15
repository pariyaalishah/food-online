package com.restaurant.foodonline.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "customer")
public class CustomerEntity extends UserEntity{
    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private Set<CartEntity> cartList = new HashSet<>();

    public Set<CartEntity> getCartList() {
        return cartList;
    }

    public void setCartList(Set<CartEntity> cartList) {
        this.cartList = cartList;
    }
}
